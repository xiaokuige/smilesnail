import React, { Component } from 'react';
import { RouteComponentProps } from "react-router-dom";
import { Layout, Row, Col, Avatar, Tabs, Collapse, List, Descriptions, Input, Button, } from 'antd';
//import UserRoomItemComponet from '../../components/userRoomList';
import '../../css/style.css';
const { Header, Content, Sider } = Layout;
const { TabPane } = Tabs;
const { Panel } = Collapse;

interface Props {
  itemSource: any
}

export default class UserRoomListComponent extends React.Component<Props> {
  render() {
    return (
      <List itemLayout="horizontal"
          bordered={false}
          dataSource={this.props.itemSource}
          renderItem={(item: any) => (
              <List.Item>
                  <List.Item.Meta
                      avatar={<Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />}
                      title={<a href="#">{item.title}</a>}
                  />
              </List.Item>
          )}
      >
      </List>
    );
  }
}