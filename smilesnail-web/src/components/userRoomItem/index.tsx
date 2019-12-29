import React, { Component } from 'react';
import { RouteComponentProps } from "react-router-dom";
import { Layout, Row, Col, Avatar, Tabs, Collapse, List, Descriptions, Input, Button, } from 'antd';
import '../../css/style.css';
const { Header, Content, Sider } = Layout;
const { TabPane } = Tabs;
const { Panel } = Collapse;

export default class UserRoomItemComponent extends React.Component {

  render() {
    return (
      <List.Item>
          <List.Item.Meta
              avatar={<Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />}
              title={<a href="#">123{item.title}</a>} />
      </List.Item>
    );
  }
}