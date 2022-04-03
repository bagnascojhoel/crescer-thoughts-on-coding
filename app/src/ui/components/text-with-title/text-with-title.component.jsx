import './styles.css';
import React from 'react';

export function TextWithTitle({ title, text, className }) {
  return (
    <div
      className={`
      text-with-title
      ${className}
      `}
    >
      <p className="text-with-title__title">
        <small>{title}</small>
      </p>
      <p className={`text-with-title__text`}>{text}</p>
    </div>
  );
}
