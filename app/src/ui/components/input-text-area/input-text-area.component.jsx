import './styles.css';
import React from 'react';

export function InputTextArea({
  register,
  label,
  placeholder,
  name,
  value,
  type,
  errorMessage,
  hasError,
  noMargin,
  min,
}) {
  return (
    <div className={`input-text-area ${noMargin && 'input-text-area--no-margin'}`}>
      {label && (
        <label className="input-text-area__label" htmlFor={name}>
          {label}
        </label>
      )}

      <textarea
        ref={register}
        className={`input-text-area__input ${
          hasError && 'input-text-area__input--error'
        }`}
        type={type || 'text-area'}
        placeholder={placeholder}
        name={name}
        id={name}
        value={value}
        min={min}
      />
      {hasError && <span className="input-text-area__error">{errorMessage}</span>}
    </div>
  );
}
