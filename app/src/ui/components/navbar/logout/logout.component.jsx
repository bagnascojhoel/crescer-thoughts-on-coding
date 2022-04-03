import React from 'react';
import { GoSignOut } from 'react-icons/go';
import { useHistory } from 'react-router-dom';
import { useGlobalUser } from '../../../../context';
import { ROUTES } from '../../../../routes';
import { SquareButton } from '../../square-button/square-button.component';

export function Logout({ iconSize }) {
  const { replace } = useHistory();
  const [_, setGlobalUser] = useGlobalUser();

  const handleClick = () => {
    setGlobalUser(null);
    replace(ROUTES.LOGIN);
  };

  return (
    <SquareButton
      icon={GoSignOut}
      onClick={handleClick}
      iconSize={iconSize}
      backgroundColor={'transparent'}
    />
  );
}
