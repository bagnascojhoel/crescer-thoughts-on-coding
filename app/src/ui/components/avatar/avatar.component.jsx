import './styles.css';
import {COLORS} from '../../styles';
import React from 'react';

export function Avatar({ url, side  }) {
  return (
    <div
      className={`avatar`}
      style={{
        width: side,
        height: side,
        backgroundImage: `url("${url}")`,
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        backgroundColor: COLORS.BLACK,
      }}
    ></div>
  );
}

Avatar.defaultProps = {
  side: '60px',
};
