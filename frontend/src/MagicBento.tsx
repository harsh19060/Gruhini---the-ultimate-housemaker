import { useEffect, useRef, type CSSProperties, type PropsWithChildren } from 'react'
import './MagicBento.css'

type MagicBentoProps = PropsWithChildren<{
  columns?: number
  rowHeight?: number
  gap?: number
  radius?: number
  enableBorderGlow?: boolean
  enableSpotlight?: boolean
  spotlightRadius?: number
  clickEffect?: boolean
}>

const CARD_SELECTOR = [
  '.stat-card', '.panel', '.bottom-summary', '.welcome-strip', '.shopping-summary',
  '.expense-hero', '.input-info-strip', '.prediction-note', '.scale-status-strip',
  '.modal-card', '.demo-banner', '.settings-panel',
  '.shopping-row', '.input-tab', '.runway-row', '.pending-reading', '.integration-row',
  '.setting-row', '.category-spend', '.lookup-result', '.extraction-preview', '.note-box',
].join(',')

/**
 * React Bits MagicBento behaviour adapted for Grihini's existing card-based UI.
 * It enhances the app's real cards rather than replacing their content or click handlers.
 */
export default function MagicBento({
  children,
  columns = 4,
  rowHeight = 200,
  gap = 12,
  radius = 20,
  enableBorderGlow = true,
  enableSpotlight = true,
  spotlightRadius = 300,
  clickEffect = true,
}: MagicBentoProps) {
  const rootRef = useRef<HTMLDivElement>(null)

  useEffect(() => {
    const root = rootRef.current
    if (!root) return
    let cards: HTMLElement[] = []
    let refreshFrame = 0
    const enhanceCards = () => {
      cards = Array.from(root.querySelectorAll<HTMLElement>(CARD_SELECTOR))
      cards.forEach(card => card.classList.add('magic-bento-card'))
    }
    const scheduleEnhance = () => {
      cancelAnimationFrame(refreshFrame)
      refreshFrame = requestAnimationFrame(enhanceCards)
    }
    enhanceCards()

    const observer = new MutationObserver(scheduleEnhance)
    observer.observe(root, { childList: true, subtree: true })

    const setPointerPosition = (card: HTMLElement, clientX: number, clientY: number) => {
      const rect = card.getBoundingClientRect()
      card.style.setProperty('--mb-x', `${clientX - rect.left}px`)
      card.style.setProperty('--mb-y', `${clientY - rect.top}px`)
      card.style.setProperty('--mb-hot', '1')
    }

    const onPointerMove = (event: PointerEvent) => {
      const target = event.target instanceof Element ? event.target.closest<HTMLElement>('.magic-bento-card') : null
      cards.forEach(card => card.style.setProperty('--mb-hot', card === target ? '1' : '0'))
      if (target) setPointerPosition(target, event.clientX, event.clientY)
    }

    const onPointerLeave = () => cards.forEach(card => card.style.setProperty('--mb-hot', '0'))

    const onClick = (event: MouseEvent) => {
      if (!clickEffect) return
      const target = event.target instanceof Element ? event.target.closest<HTMLElement>('.magic-bento-card') : null
      if (!target) return
      setPointerPosition(target, event.clientX, event.clientY)
      target.classList.remove('magic-bento-wave')
      void target.offsetWidth
      target.classList.add('magic-bento-wave')
    }

    root.addEventListener('pointermove', onPointerMove)
    root.addEventListener('pointerleave', onPointerLeave)
    root.addEventListener('click', onClick)
    return () => {
      root.removeEventListener('pointermove', onPointerMove)
      root.removeEventListener('pointerleave', onPointerLeave)
      root.removeEventListener('click', onClick)
      observer.disconnect()
      cancelAnimationFrame(refreshFrame)
      cards.forEach(card => card.classList.remove('magic-bento-card', 'magic-bento-wave'))
    }
  }, [clickEffect])

  return <div ref={rootRef} className="magic-bento" style={{ '--mb-columns': columns, '--mb-row-height': `${rowHeight}px`, '--mb-gap': `${gap}px`, '--mb-radius': `${radius}px`, '--mb-spotlight-radius': `${spotlightRadius}px`, '--mb-border-glow': enableBorderGlow ? 1 : 0, '--mb-spotlight': enableSpotlight ? 1 : 0 } as CSSProperties}>{children}</div>
}
