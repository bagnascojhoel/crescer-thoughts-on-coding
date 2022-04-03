import './styles.css';
import React from 'react';

export function FloatingBox({ minWidth, className, children }) {
  return (
    <div className={`floating-box ${className}`} style={{ minWidth }}>
      {children}
    </div>
  );
}
