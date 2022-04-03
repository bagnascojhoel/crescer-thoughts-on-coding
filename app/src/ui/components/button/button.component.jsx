import './styles.css';
import React from 'react';

export function Button({ onClick, className, disabled, children }) {
  return (
    <button
      onClick={onClick}
      className={`button ${className} ${disabled && 'button--disabled'}`}
      disabled={disabled}
    >
      {children}
    </button>
  );
}
