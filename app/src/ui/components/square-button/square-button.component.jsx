import './styles.css';
import React from 'react';
import { MdAdd } from 'react-icons/md';
import { COLORS } from '../../styles';
import { Link } from 'react-router-dom';

export function SquareButton({
  to,
  onClick,
  icon,
  iconSize,
  iconColor,
  backgroundColor,
}) {
  return (
    <>
      {to ? (
        <Link to={to} className="square-button" style={{ backgroundColor }}>
          <SquareButtonIcon icon={icon} size={iconSize} color={iconColor} />
        </Link>
      ) : (
        <button
          className="square-button"
          onClick={onClick}
          style={{ backgroundColor }}
        >
          <SquareButtonIcon icon={icon} size={iconSize} color={iconColor} />
        </button>
      )}
    </>
  );
}

function SquareButtonIcon({ icon, size, color }) {
  return React.createElement(icon, {
    className: 'square-button__icon',
    color: color || COLORS.BLACK,
    size,
  });
}

SquareButton.defaultProps = {
  onClick: () => {},
  icon: MdAdd,
  iconSize: 30,
};
