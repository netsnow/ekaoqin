import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { tap, map } from 'rxjs/operators';
import { STComponent, STColumn, STData } from '@delon/abc';
import { userData } from './user-mockdata';
import { UserService } from '../../common/service/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
})
export class UserListComponent implements OnInit {
  fullname = "";
  users = userData;
  data: any = {};
  params: any = { fullname: '123', usertype: 'ROLE_USER' };
  loading = false;
  modalUsername = "";
  modalFullname = "";
  modalUserType = "";
  //status = [
  //  { index: 0, text: '禁用', value: false, type: 'error', checked: false },
  //  { index: 1, text: '正常', value: false, type: 'success', checked: false },
  //];
  userType = [
    { index: 'ROLE_ADMIN', text: '管理员' },
    { index: 'ROLE_USER', text: '教师' },
  ];
  userClass = [
    { index: 0, text: '一班' },
    { index: 1, text: '二班' },
    { index: 2, text: '三班' },
  ];
  userRoom = [
    { index: 0, text: 'A楼101' },
    { index: 1, text: 'A楼102' },
    { index: 2, text: 'B楼101' },
  ];
  @ViewChild('st')
  st: STComponent;
  columns: STColumn[] = [
    { title: '', index: 'key', type: 'checkbox' },
    { title: '用户名', index: 'username' },
    { title: '用户姓名', index: 'fullname' },
    { title: '用户种类', index: 'usertype', render: 'usertype' },
    {
      title: '操作',
      render: 'operation',
    },
  ];
  selectedRows: STData[] = [];
  description = '';
  totalCallNo = 0;
  expandForm = false;

  constructor(
    private http: _HttpClient,
    public msg: NzMessageService,
    private modalSrv: NzModalService,
    private userService: UserService,
  ) { }

  ngOnInit() {
    this.getData();
  }

  getData() {
    this.loading = true;
    this.userService.getAllUsers().subscribe(
      resp => {
        this.loading = false;
        this.data = resp;
        console.log(resp);

      },
      error => {
        this.loading = false;
        console.log(error);
      }
    )
    //this.data = this.users;
  }

  checkboxChange(list: STData[]) {
    this.selectedRows = list;
    this.totalCallNo = this.selectedRows.reduce(
      (total, cv) => total + cv.callNo,
      0,
    );
  }

  remove(id) {
    this.userService.remove(id).subscribe(
      resp=>{
        this.msg.success("已删除！");
        this.getData();
      },
      error=>{
        this.msg.error("删除失败！");
        console.log(error)
      }
    )
  }

  add(tpl: TemplateRef<{}>) {
    this.modalUsername = '';
    this.modalFullname = '';
    this.modalUserType = 'ROLE_USER';
    this.modalSrv.create({
      nzTitle: '新建用户（初始密码为：111111）',
      nzContent: tpl,
      nzOnOk: () => {
        this.loading = true;
        var userInfo = {};
        userInfo["username"] = this.modalUsername;
        userInfo["fullname"] = this.modalFullname;
        userInfo["password"] = '111111';
        userInfo["enabled"] = true;
        var userAuthoritys = [{}];
        userAuthoritys[0]["name"] = this.modalUserType;
        userInfo["authorities"] = userAuthoritys;
        console.log(userInfo);
        this.userService.saveUser(userInfo)
          .subscribe(
            resp => {
              this.loading = false;
              this.msg.success("已保存！");
              this.getData();
            },
            error => {
              this.loading = false;
              this.msg.error(error.error.message);
              console.log(error);
            }
          );
      },
    });
  }
  edit(tpl: TemplateRef<{}>, id, username, fullname, usertype) {
    this.modalUsername = username;
    this.modalFullname = fullname;
    this.modalUserType = usertype;

    this.modalSrv.create({
      nzTitle: '编辑用户',
      nzContent: tpl,
      nzOnOk: () => {
        this.loading = true;
        var userInfo = {};
        userInfo["id"] = id;
        userInfo["username"] = this.modalUsername;
        userInfo["fullname"] = this.modalFullname;
        var userAuthoritys = [{}];
        userAuthoritys[0]["name"] = this.modalUserType;
        userInfo["authorities"] = userAuthoritys;
        console.log(userInfo);
        this.userService.editUser(id, userInfo)
          .subscribe(
            resp => {
              this.loading = false;
              this.msg.success("已保存！");
              this.getData();
            },
            error => {
              this.loading = false;
              this.msg.error(error.error.message);
              console.log(error);
            }
          );
      },
    });
  }
  reset(ls: any[]) {
    for (const item of ls) item.value = false;
    this.getData();
  }
}
