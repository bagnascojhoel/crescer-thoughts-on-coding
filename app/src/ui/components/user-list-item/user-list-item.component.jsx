import React from 'react';
import { Avatar } from '../avatar/avatar.component';
import { TextWithTitle } from '../text-with-title/text-with-title.component';


export function UserListItem({ data }) {
  const { profilePhoto, name, email } = data;

  return (
    <>
      <Avatar url={profilePhoto}/>
      <TextWithTitle title="Name" text={name}/>
      <TextWithTitle title="Email" text={email}/>
    </>
  );
}
