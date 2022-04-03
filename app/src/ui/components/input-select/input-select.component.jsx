import './styles.css';
import React from 'react';

export function InputSelect({
  register,
  hasError,
  errorMessage,
  options,
  name,
  label,
}) {

  return (
    <div className="input-select">
      {label && (
        <label className="input-select__label" htmlFor={name}>
          {label}
        </label>
      )}

      <select
        ref={register}
        className="input-select__input"
        name={name}
        id={name}
      >
        <option defaultValue disabled>
          {label}
        </option>
        {options.map((opt) => (
          <option value={opt.value} key={opt.value}>
            {opt.text}
          </option>
        ))}
      </select>
      {hasError && <span className="input-text__error">{errorMessage}</span>}
    </div>
  );
}

InputSelect.defaultProps = {
  options: [],
};
