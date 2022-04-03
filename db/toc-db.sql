drop table toc_user;

create table toc_user (
  user_id number generated always as identity,
  name varchar(255) not null,
  email varchar(255) not null,
  birth_date date not null,
  nickname varchar(50),
  profile_photo varchar(512), 
  constraint pk_toc_user primary key (user_id),
  constraint un_toc_user_email unique(email)
);

insert into toc_user (name, email, birth_date, nickname, profile_photo)
values ('Martin Fowler', 'martin@fowler.com', '18-12-1963', 'Martin', 'https://s2.glbimg.com/1piTiw8-ZHLoJrlB2p7P5DLM0vw=/620x350/e.glbimg.com/og/ed/f/original/2019/08/21/martincap1.png');

insert into toc_user (name, email, birth_date, nickname)
values ('Robert C. Martin', 'rober@cleancode.net', '05-12-1952', 'Uncle Bob');

insert into toc_user (name, email, birth_date, profile_photo)
values ('Fred Brooks', 'fb@manmonth.com', '19-04-1931', 'https://media.springernature.com/lw685/springer-static/image/art%3A10.1007%2Fs12599-010-0104-x/MediaObjects/12599_2010_104_Figa_HTML.jpg');

insert into toc_user (name, email, birth_date)
values ('Erich Gamma', 'gammaerich@outlook.com', '13-03-1961');


--------------------------------------------------------------------------------

drop table toc_friendship;

create table toc_friendship (
  friendship_id number generated always as identity,
  sender_id number not null,
  receiver_id number not null,
  status varchar(30) not null,
  last_status_update timestamp default current_timestamp not null,
  constraint pk_toc_friendship primary key (friendship_id),
  constraint fk_toc_friendship_sender_id foreign key (sender_id) references toc_user (user_id),
  constraint fk_toc_friendship_receiver_id foreign key (receiver_id) references toc_user (user_id),
  constraint cc_toc_friendship_status check (status in ('PENDING', 'DENIED', 'ACCEPTED'))
);
--
--drop trigger tr_no_repeated_toc_friendship;
--
--create or replace trigger tr_no_repeated_toc_friendship
--  before insert on toc_friendship
--  for each row
--declare
--    v_temp number;   
--    v_is_valid boolean := false; 
--begin
--
--  if (:new.sender_id = :new.receiver_id) then
--    raise_application_error(-20111, 'A User Cannot Befriend Herself');
--  end if;
--
--  begin 
--
--    select friendship_id
--    into v_temp
--    from toc_friendship
--    where
--      (sender_id = :new.sender_id and receiver_id = :new.receiver_id)
--      or (sender_id = :new.receiver_id and receiver_id = :new.sender_id);
--
--  exception
--    when NO_DATA_FOUND then
--      v_is_valid := true;
--  end;
--
--  if (v_is_valid) then
--    dbms_output.put_line('Added with success');
--  else
--    raise_application_error(-20112, 'This Friendship Aleady Exists');
--  end if;
--
--end;

--------------------------------------------------------------------------------

drop table toc_post;

create table toc_post (
  post_id number generated always as identity,
  author_id number not null,
  comment_to_id number,
  content varchar(512) not null,
  creation timestamp default CURRENT_TIMESTAMP,
  visibility varchar(15) not null,
  constraint pk_toc_post primary key (post_id),
  constraint fk_toc_post_author_id foreign key (author_id) references toc_user (user_id),
  constraint fk_toc_post_comment_to_id foreign key (comment_to_id) references toc_post (post_id),
  constraint cc_toc_post_visibility check (visibility in ('PRIVATE', 'PUBLIC'))
);

insert into toc_post (author_id, content, visibility)
values (1, 'Programming: when the ideas turn into the real things.', 'PUBLIC');

insert into toc_post (author_id, content, visibility)
values (3, 'Complexity is the death of software.', 'PRIVATE');

insert into toc_post (author_id, content, visibility)
values (3, 'Rewriting is an opportunity to find new ways of coding old bugs, as well as exciting new bugs.', 'PUBLIC');

insert into toc_post (author_id, content, visibility)
values (4, 'If you give someone a program, you will frustrate them for a day; if you teach them how to program, you will frustrate them for a lifetime.', 'PUBLIC');

insert into toc_post (author_id, content, visibility, comment_to_id)
values (2, 'Bleh.', 'PUBLIC', 1);

insert into toc_post (author_id, content, visibility, comment_to_id)
values (4, 'Great mind.', 'PUBLIC', 2);

insert into toc_post (author_id, content, visibility, comment_to_id)
values (2, 'Potato is life, forget coding.', 'PUBLIC', 3);

insert into toc_post (author_id, content, visibility, comment_to_id)
values (4, 'I liked it.', 'PUBLIC', 4);

--------------------------------------------------------------------------------

drop table toc_like;

create table toc_like (
    post_id number not null,
    user_id number not null,
    constraint pk_toc_like primary key (post_id, user_id),
    constraint fk_toc_like_post foreign key (post_id) references toc_post (post_id),
    constraint fk_toc_like_user foreign key (user_id) references toc_user (user_id)
);
