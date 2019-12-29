import React, { Component } from 'react';
import { RouteComponentProps } from "react-router-dom";
import { Layout, Row, Col, Avatar, Tabs, Collapse, List, Descriptions, Input, Button, } from 'antd';
import '../../css/style.css';
const { Header, Content, Sider } = Layout;
const { TabPane } = Tabs;
const { Panel } = Collapse;

export default class LoginComponent extends React.Component {

  state = {};

  render() {
    return (
      <Header style={{ height: "64px", background: '#fff', boxShadow: '0 2px 8px #f0f1f2' }}>
          <Row type="flex" style={{ height: '64px', alignItems: 'center', justifyContent: 'center' }}>
              <Col span={12}>
                  <div className="logo"></div>
              </Col>
              <Col span={12} style={{ display: "flex", flexDirection: 'row-reverse', alignItems: 'center', lineHeight: "64px" }}>
                  <span style={{ marginLeft: '6px' }}>zhangfeng</span><Avatar>Z</Avatar>
              </Col>
          </Row>
      </Header>
    );
  }
}
