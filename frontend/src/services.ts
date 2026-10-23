export const API_BASE_URL = (import.meta.env.VITE_API_BASE_URL as string | undefined)?.replace(/\/$/, '') ?? ''
export const isApiConfigured = API_BASE_URL.length > 0

/** A small API wrapper to use when Spring Boot contracts are ready. Demo mode is intentionally the default. */
export async function apiRequest<T>(path: string, init?: RequestInit): Promise<T> {
  if (!API_BASE_URL) throw new Error('API URL is not configured. The frontend is currently in Demo Mode.')
  const response = await fetch(`${API_BASE_URL}${path}`, {
    ...init,
    headers: { 'Content-Type': 'application/json', ...(init?.headers ?? {}) },
  })
  if (!response.ok) throw new Error(`API request failed (${response.status})`)
  if (response.status === 204) return undefined as T
  return response.json() as Promise<T>
}
