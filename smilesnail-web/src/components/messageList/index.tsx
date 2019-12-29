import React, { Component } from 'react';
import { RouteComponentProps } from "react-router-dom";
import { Layout, Row, Col, Avatar, Tabs, Collapse, List, Descriptions, Input, Button, } from 'antd';
import '../../css/style.css';
const { Header, Content, Sider } = Layout;
const { TabPane } = Tabs;
const { Panel } = Collapse;

interface Props {
  chats: any
}

export default class MessageListComponent extends React.Component<Props> {
  render() {
  	const listItems = this.props.chats.map((chat: any) => {
		return <div className={ ['avc-msg-item', chat.chatFrom === 'own' ? 'own-msg' : '', chat.chatFrom === 'buddy' ? 'buddy-msg' : ''].join(' ') }
					key={chat.id.toString()}>
				    <div className="inner">
				        <a href="#" className="manImg">
				        	<img alt="" className="" width="40" height="40" src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />
				        </a>
				        <div className="contentBox">
				            {chat.content}
				        </div>
				    </div>
				</div>
	});
    return (
      <div>
		{listItems}
      </div>
    );
  }
}