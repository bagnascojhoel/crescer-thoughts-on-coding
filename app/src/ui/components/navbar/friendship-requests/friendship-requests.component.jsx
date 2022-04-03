import './styles.css';
import React, { useEffect, useState } from 'react';
import { useFriendshipApi } from '../../../../hooks';
import { DataList, SquareButton, TextWithTitle } from '../../';
import { MdCheck, MdClose, MdPeople } from 'react-icons/md';

export function FriendshipRequests() {
  const {
    listMyFriendshipRequests,
    acceptRequest,
    denyRequest,
  } = useFriendshipApi();
  const [showRequests, setShowRequests] = useState(false);
  const [requests, setRequests] = useState([]);

  useEffect(() => loadRequests(), []);

  const loadRequests = async () => {
    const response = await listMyFriendshipRequests();
    console.log(response)
    setRequests(response || []);
  };

  const handleAccept = async (id) => {
    await acceptRequest(id);
    loadRequests();
  };

  const handleDeny = async (id) => {
    await denyRequest(id);
    loadRequests();
  };

  const handleShowRequests = () => setShowRequests(!showRequests);

  return (
    <div className="friendship-requests">
      <SquareButton icon={MdPeople} onClick={handleShowRequests} />
      <List
        requests={requests}
        onAccept={handleAccept}
        onDeny={handleDeny}
        show={showRequests}
      />
    </div>
  );
}

function List({ requests, onAccept, onDeny, show }) {
  return (
    <div
      className={`
      friendship-requests__list
      ${show && 'friendship-requests__list--visible'}`}
    >
      <DataList
        dataList={requests}
        itemComponent={ItemList}
        iconSize={20}
        noDataMessage="No friendship requests"
        onAccept={onAccept}
        onDeny={onDeny}
      />
    </div>
  );
}

function ItemList({ data, iconSize, onAccept, onDeny }) {
  const _onAccept = () => onAccept(data.id);

  const _onDeny = () => onDeny(data.id);

  return (
    <>
      <TextWithTitle title="Sender" text={data.sender.name} />
      <SquareButton icon={MdCheck} onClick={_onAccept} iconSize={iconSize} />
      <SquareButton icon={MdClose} onClick={_onDeny} iconSize={iconSize} />
    </>
  );
}
