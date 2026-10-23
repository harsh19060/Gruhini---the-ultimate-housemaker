import { useEffect, useLayoutEffect, useRef, useState, type CSSProperties, type MouseEvent, type ReactNode } from 'react'
import './PillNav.css'
import './PillNavOverrides.css'

export type PillNavItem = { label: string; href: string; icon?: ReactNode; ariaLabel?: string }

type PillNavProps = {
  logo?: string | ReactNode
  logoAlt?: string
  logoHref?: string
  items: PillNavItem[]
  activeHref?: string
  onItemClick?: (item: PillNavItem, index: number, event: MouseEvent<HTMLAnchorElement>) => void
  theme?: 'light' | 'dark'
  itemStyle?: 'pill' | 'plain'
  hoverEffect?: 'fill' | 'none'
  indicator?: 'dot' | 'none'
  className?: string
  style?: CSSProperties
}

/** React Bits PillNav adapted to this Vite/TypeScript application. */
export default function PillNav({
  logo,
  logoAlt = 'Logo',
  logoHref = '#',
  items,
  activeHref,
  onItemClick,
  theme = 'light',
  itemStyle = 'pill',
  hoverEffect = 'fill',
  indicator = 'dot',
  className = '',
  style,
}: PillNavProps) {
  const navRef = useRef<HTMLElement>(null)
  const [collapsed, setCollapsed] = useState(false)
  const [open, setOpen] = useState(false)

  useLayoutEffect(() => {
    const nav = navRef.current
    if (!nav) return
    const update = () => setCollapsed(nav.parentElement ? nav.scrollWidth > nav.parentElement.clientWidth : false)
    const observer = new ResizeObserver(update)
    observer.observe(nav.parentElement ?? nav)
    update()
    return () => observer.disconnect()
  }, [items.length])

  useEffect(() => { if (!collapsed) setOpen(false) }, [collapsed])

  const select = (item: PillNavItem, index: number, event: MouseEvent<HTMLAnchorElement>) => {
    onItemClick?.(item, index, event)
    setOpen(false)
  }

  return (
    <nav ref={navRef} className={`pill-nav pill-nav--${theme} ${className}`} data-items={itemStyle} data-hover={hoverEffect} data-open={open || undefined} style={style} aria-label="Primary navigation">
      {logo && <a className="pill-nav__logo" href={logoHref} aria-label={logoAlt}>{typeof logo === 'string' ? <img src={logo} alt="" /> : logo}</a>}
      {!collapsed && <div className="pill-nav__bar"><ul className="pill-nav__list">{items.map((item, index) => <li key={item.href}><a className="pill-nav__item" href={item.href} aria-label={item.ariaLabel} aria-current={item.href === activeHref ? 'page' : undefined} onClick={event => select(item, index, event)}>{item.icon}<span>{item.label}</span>{indicator === 'dot' && item.href === activeHref && <i className="pill-nav__dot" />}</a></li>)}</ul></div>}
      {collapsed && <button className="pill-nav__toggle" type="button" aria-label={open ? 'Close navigation menu' : 'Open navigation menu'} aria-expanded={open} onClick={() => setOpen(value => !value)}><i/><i/></button>}
      {collapsed && open && <div className="pill-nav__panel"><ul>{items.map((item, index) => <li key={item.href}><a href={item.href} aria-current={item.href === activeHref ? 'page' : undefined} onClick={event => select(item, index, event)}>{item.icon}<span>{item.label}</span></a></li>)}</ul></div>}
    </nav>
  )
}
