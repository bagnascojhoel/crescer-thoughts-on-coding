import React from 'react';
import { MdArrowBack } from 'react-icons/md';
import { useHistory } from 'react-router-dom';
import { COLORS } from '../../../styles';
import { SquareButton } from '../../square-button/square-button.component';

export function GoBack({ iconSize }) {
  const { goBack } = useHistory();

  const handleClick = () => {
    goBack();
  };

  return (
    <SquareButton
      onClick={handleClick}
      icon={MdArrowBack}
      iconSize={iconSize}
      backgroundColor={'transparent'}
    />
  );
}
