import './styles.css';
import React, { createRef, useEffect, useRef, useState } from 'react';
import { MdClear, MdSearch } from 'react-icons/md';
import { useHistory } from 'react-router-dom';
import { ROUTES } from '../../../../routes';
import { SquareButton } from '../../square-button/square-button.component';
import { COLORS } from '../../../styles';

export function SearchField({ iconSize }) {
  const [value, setValue] = useState('');
  const {
    push,
    replace,
    location: { pathname },
  } = useHistory();

  useEffect(() => {
    const queryParam = `?query=${value}`;

    if (pathname === ROUTES.SEARCH_RESULT)
      replace(ROUTES.SEARCH_RESULT + queryParam);
    else if (value !== '') {
      push(ROUTES.SEARCH_RESULT + queryParam);
    }
  }, [value]);

  const handleChange = (e) => setValue(e.target.value);

  const handleClear = () => setValue('');

  return (
    <div className="search-field">
      <div className="search-field__content">
        <MdSearch size={iconSize} className="search-field__search-icon" />
        <input
          type="text"
          value={value}
          onChange={handleChange}
          className="search-field__input"
        />
      </div>
      <SquareButton
        icon={MdClear}
        iconSize={iconSize}
        iconColor={COLORS.GRAY}
        onClick={handleClear}
      />
    </div>
  );
}
