import './styles.css';
import React from 'react';
import { Avatar, SquareButton, TextWithTitle } from '../';
import { MdEdit, MdPeople, MdPerson, MdPersonAdd } from 'react-icons/md';
import { BsFillPersonDashFill } from 'react-icons/bs';
import { ROUTES } from '../../../routes';
import { FRIENDSHIP_STATUS } from '../../../hooks';

const iconSize = 30;

export function Profile({ user, isMyself, existFriendship, onSendRequest, onUndoFriendship }) {
  return user ? (
    <div className="profile">
      <div className="profile__avatar">
        <Avatar url={user.profilePhoto} side={150} />
      </div>
      <div className="profile__content">
        <ProfileContentHeader user={user} />
        <ProfileContentBody user={user} />
      </div>
      <ProfileActions
        existFriendship={existFriendship}
        isMyself={isMyself}
        onSendRequest={onSendRequest}
        onUndoFriendship={onUndoFriendship}
      />
    </div>
  ) : (
    <></>
  );
}

function ProfileContentHeader({ user }) {
  return (
    <div className="profile__content__header">
      <h2 className="profile__name">{user.nickname || user.name}</h2>
    </div>
  );
}

function ProfileActions({ isMyself, existFriendship, onSendRequest, onUndoFriendship }) {
  const ActionsWhenMyself = (
    <>
      <SquareButton
        to={ROUTES.PROFILE_EDITION}
        icon={MdEdit}
        iconSize={iconSize}
      />
      <SquareButton to={ROUTES.FRIENDS} icon={MdPeople} iconSize={iconSize} />
    </>
  );

  const ActionsWhenSomeoneElse =
    existFriendship === false ? (
      <SquareButton
        icon={MdPersonAdd}
        iconSize={iconSize}
        onClick={onSendRequest}
      />
    ) : (
      <SquareButton
        icon={BsFillPersonDashFill}
        iconSize={iconSize}
        onClick={onUndoFriendship}
      />
    );

  return (
    <div className="profile__actions">
      {isMyself ? ActionsWhenMyself : ActionsWhenSomeoneElse}
    </div>
  );
}

function ProfileContentBody({ user }) {
  return (
    <div>
      {user.nickname && (
        <TextWithTitle
          title="Name"
          text={user.name}
          className="profile__content__item"
        />
      )}

      <TextWithTitle
        title="Email"
        text={user.email}
        className="profile__content__item"
      />
      <TextWithTitle
        title="Birth Date"
        text={user.birthDate}
        className="profile__content__item"
      />
    </div>
  );
}
