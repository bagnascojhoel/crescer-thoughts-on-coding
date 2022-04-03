import './styles.css';
import React from 'react';
import { Link } from 'react-router-dom';

export function DataList({
  noDataMessage,
  dataList,
  itemComponent,
  keyExtractor,
  onClick,
  to,
  ...props
}) {
  // console.log(onClick);
  return (
    <ul className="data-list">
      {dataList.length > 0 ? (
        <List
          list={dataList}
          itemComponent={itemComponent}
          keyExtractor={keyExtractor}
          onClick={onClick}
          to={to}
          {...props}
        />
      ) : (
        <EmptyList message={noDataMessage} />
      )}
    </ul>
  );
}

function EmptyList({ message }) {
  return (
    <li className="data-list-item">
      <h3 className="data-list-item__text">{message}</h3>
    </li>
  );
}

function List({ list, onClick, to, keyExtractor, ...props }) {
  return list.map((data) =>
    onClick || to ? (
      <ListItemButton
        data={data}
        onClick={onClick}
        to={to}
        key={keyExtractor(data)}
        {...props}
      />
    ) : (
      <ListItem data={data} key={keyExtractor(data)} {...props} />
    )
  );
}

function ListItem({ itemComponent, data, ...props }) {
  return (
    <li className="data-list-item">
      {React.createElement(itemComponent, { data, ...props })}
    </li>
  );
}

function ListItemButton({ onClick, to, itemComponent, data, props }) {
  const _onClick = () => onClick(data);

  const child = React.createElement(itemComponent, { data, ...props });

  return (
    <li className="data-list-item-button">
      {to ? (
        <Link to={to} onClick={onClick || (() => {})}>
          {child}
        </Link>
      ) : (
        <button onClick={_onClick} className="data-list-item-button__button">
          {child}
        </button>
      )}
    </li>
  );
}

DataList.defaultProps = {
  dataList: [],
  keyExtractor: () => {},
  noDataMessage: 'No items on the list',
};
