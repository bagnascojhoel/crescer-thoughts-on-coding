import React from 'react';
import { DataList } from '../data-list/data-list.component';
import { LayoutContainer } from '../layout-container/layout-container.component';
import { Post } from './post/post.component';

export function Timeline({ posts, onLike, myself, onUpdateVisibility, onCreateComment }) {
  return (
    <>
      <LayoutContainer centerColumn>
        <DataList
          dataList={posts}
          itemComponent={Post}
          keyExtractor={({ id }) => id}
          onLike={onLike}
          myself={myself}
          onUpdateVisibility={onUpdateVisibility}
          onCreateComment={onCreateComment}
        />
      </LayoutContainer>
    </>
  );
}
