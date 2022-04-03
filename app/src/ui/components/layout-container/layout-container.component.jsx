import './styles.css';
import React from 'react';

export function LayoutContainer({
  className,
  centerColumn,
  children,
}) {
  return (
    <div
      className={`
      layout-container
      ${centerColumn && 'layout-container--center-column'}
      ${className}
      `}
    >
      {children}
    </div>
  );
}
