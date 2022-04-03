import './styles.css';
import React from 'react';

export function InputText({
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
    <div className={`input-text ${noMargin && 'input-text--no-margin'}`}>
      {label && (
        <label className="input-text__label" htmlFor={name}>
          {label}
        </label>
      )}

      <input
        ref={register}
        className={`input-text__input ${
          hasError && 'input-text__input--error'
        }`}
        type={type || 'text'}
        placeholder={placeholder}
        name={name}
        id={name}
        value={value}
        min={min}
      />
      {hasError && <span className="input-text__error">{errorMessage}</span>}
    </div>
  );
}
