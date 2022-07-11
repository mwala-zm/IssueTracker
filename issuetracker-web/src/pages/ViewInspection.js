import axios from 'axios';
import React from 'react';
import { Space, Table } from 'antd';

const columns = [
  {
    title: 'Name',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: 'Status',
    dataIndex: 'status',
    key: 'status',
  },
  {
    title: 'Description',
    dataIndex: 'description',
    key: 'description',
  },
  {
    title: 'Date',
    dataIndex: 'date',
    key: 'date',
  },
  {
    title: 'Location',
    dataIndex: ['equipment', 'location'],
    key: 'location',
  },
  {
    title: 'State',
    dataIndex: ['state', 'name'],
    key: 'name',
  },
  {
    title: 'Submitted by',
    dataIndex: ['users', 'username'],
    key: 'username',
  },
  {
    title: 'Action',
    key: 'action',
    render: (_, record) => (
      <Space size="middle">
        <a href='/'>Inspect {record.name}</a>
        <a href='/'>Delete</a>
      </Space>
    ),
  },
];

const rowSelection = {
  onChange: (selectedRowKeys, selectedRows) => {
    console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
  },
  getCheckboxProps: (record) => ({
    disabled: record.name === 'Disabled User',
    // Column configuration not to be checked
    name: record.name,
  }),
};

class ViewInspection extends React.Component {
    // constructor(props) {
    //   super(props);
    //   this.state = {};
    // }

    state = {
    }

    componentDidMount() {
      let currentComponent = this;
      console.log(this.props.state)
      var config = {
        method: 'get',
        url: 'http://localhost:8080/inspections/',
        headers: { 
          'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtd2l6YSIsImV4cCI6MTY1NzMxMTQyNSwiaWF0IjoxNjU3MjI1MDI1LCJhdXRob3JpdGllcyI6WyJJbnNwZWN0b3IiLCJTdXBlcnZpc29yIl19.ddhLNhjQTTrmKDckbtFUe6KBP4dMrTfT_mxmVWR7O7fL9RecjKCNX3FEwaH08yOtu3n1T_f8Qct20DUOdUO9qA', 
          'Content-Type': 'application/json'
        }
      };
      
      axios(config)
      .then(function (response) {
        // console.log(JSON.stringify(response.data));
        // console.log(config)
        const data = response.data._embedded.inspectionModelList;
        currentComponent.setState({ data });
        // console.log(this.state.data)
      })
      .catch(function (error) {
        console.log(error);
      });
    }

    render(){
      let x = this.state.data;
      let table = [];
      // x.map(y => console.log(y));
      console.log(x);
      // x.map((inspection) =>
      //   console.log(inspection)  
      // )
      // console.log(this.props.state)
      return (
        <div>
          
          {x && x.map((product) => (table.push(product)))}
          <h2>Tracker</h2>
          <Table 
            columns={columns} 
            dataSource={table}
            rowSelection={{
              type: "checkbox",
              ...rowSelection,
            }}
          />
        </div>
      );
    }
  }

export default ViewInspection;