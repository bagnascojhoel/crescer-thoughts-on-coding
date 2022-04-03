import './styles.css';
import React from 'react';
import { LayoutContainer } from '../';
import { GoBack } from './go-back/go-back.component';
import { Logout } from './logout/logout.component';
import { SearchField } from './search-field/search-field.component';
import { SquareButton } from '../square-button/square-button.component';
import { ROUTES } from '../../../routes';
import { MdHome } from 'react-icons/md';
import { FriendshipRequests } from './friendship-requests/friendship-requests.component';

export function Navbar() {
  const iconSize = 24;

  return (
    <nav className="navbar">
      <LayoutContainer className="navbar-container">
        <GoBack iconSize={iconSize} />
        <GoHome iconSize={iconSize} />
        <SearchField iconSize={iconSize} />
        <FriendshipRequests />
        <Logout iconSize={iconSize} />
      </LayoutContainer>
    </nav>
  );
}

function GoHome({ iconSize }) {
  return (
    <SquareButton
      to={ROUTES.HOME}
      icon={MdHome}
      iconSize={iconSize}
      backgroundColor="transparent"
    />
  );
}
