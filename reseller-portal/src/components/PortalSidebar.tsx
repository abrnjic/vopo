"use client";

import { useEffect, useState } from 'react';
import { ChevronLeft, ChevronRight, Menu, X, type LucideIcon } from 'lucide-react';

export type PortalNavigationItem<T extends string> = {
  id: T;
  label: string;
  icon: LucideIcon;
};

export default function PortalSidebar<T extends string>({
  title,
  items,
  activeTab,
  onSelect,
}: {
  title: string;
  items: PortalNavigationItem<T>[];
  activeTab: T;
  onSelect: (tab: T) => void;
}) {
  const [collapsed, setCollapsed] = useState(false);
  const [mobileOpen, setMobileOpen] = useState(false);

  useEffect(() => {
    setCollapsed(window.localStorage.getItem('vopo-sidebar-collapsed') === 'true');
  }, []);

  useEffect(() => {
    if (!mobileOpen) return;
    const onEscape = (event: KeyboardEvent) => {
      if (event.key === 'Escape') setMobileOpen(false);
    };
    window.addEventListener('keydown', onEscape);
    return () => window.removeEventListener('keydown', onEscape);
  }, [mobileOpen]);

  const toggleCollapsed = () => {
    setCollapsed(previous => {
      window.localStorage.setItem('vopo-sidebar-collapsed', String(!previous));
      return !previous;
    });
  };

  return (
    <>
      <button
        type="button"
        aria-label="Otvori izbornik"
        aria-expanded={mobileOpen}
        aria-controls="portal-sidebar"
        onClick={() => setMobileOpen(true)}
        className="fixed bottom-5 left-5 z-40 flex h-12 w-12 items-center justify-center rounded-2xl border border-blue-400/40 bg-blue-600 text-white shadow-xl shadow-blue-950/50 md:hidden"
      >
        <Menu className="h-5 w-5" />
      </button>
      {mobileOpen && <button type="button" aria-label="Zatvori izbornik" onClick={() => setMobileOpen(false)} className="fixed inset-x-0 bottom-0 top-16 z-40 bg-black/70 md:hidden" />}
      <aside
        id="portal-sidebar"
        aria-label={title}
        className={`fixed bottom-0 left-0 top-16 z-50 flex w-72 flex-col border-r border-gray-700/70 bg-[#121a2a] shadow-2xl transition-transform duration-200 md:sticky md:top-24 md:z-20 md:h-[calc(100vh-8rem)] md:shrink-0 md:translate-x-0 md:rounded-2xl md:border ${collapsed ? 'md:w-[76px]' : 'md:w-60'} ${mobileOpen ? 'translate-x-0' : '-translate-x-full'}`}
      >
        <div className="flex min-h-20 items-center justify-between gap-2 border-b border-gray-700/60 px-4">
          <div className={`min-w-0 ${collapsed ? 'md:hidden' : ''}`}>
            <p className="truncate text-xs font-semibold uppercase tracking-[0.2em] text-blue-400">VOPO PORTAL</p>
            <h2 className="mt-1 truncate text-base font-bold text-white">{title}</h2>
          </div>
          <button type="button" onClick={() => setMobileOpen(false)} aria-label="Zatvori izbornik" className="rounded-lg p-2 text-gray-300 hover:bg-gray-800 md:hidden"><X className="h-5 w-5" /></button>
          <button type="button" onClick={toggleCollapsed} aria-label={collapsed ? 'Proširi bočni izbornik' : 'Sakrij bočni izbornik'} aria-expanded={!collapsed} className="hidden rounded-lg p-2 text-gray-400 hover:bg-gray-800 hover:text-white md:block">{collapsed ? <ChevronRight className="h-5 w-5" /> : <ChevronLeft className="h-5 w-5" />}</button>
        </div>
        <nav className="flex-1 space-y-1 overflow-y-auto p-3" aria-label="Odjeljci portala">
          {items.map(({ id, label, icon: Icon }) => (
            <button
              key={id}
              type="button"
              title={collapsed ? label : undefined}
              aria-label={label}
              aria-current={activeTab === id ? 'page' : undefined}
              onClick={() => { onSelect(id); setMobileOpen(false); }}
              className={`flex w-full items-center gap-3 rounded-xl px-3 py-3 text-left text-sm font-semibold transition-colors ${activeTab === id ? 'bg-blue-600 text-white shadow-lg shadow-blue-950/40' : 'text-gray-400 hover:bg-gray-800 hover:text-white'} ${collapsed ? 'md:justify-center' : ''}`}
            >
              <Icon className="h-5 w-5 shrink-0" aria-hidden="true" />
              <span className={`truncate ${collapsed ? 'md:hidden' : ''}`}>{label}</span>
            </button>
          ))}
        </nav>
      </aside>
    </>
  );
}
