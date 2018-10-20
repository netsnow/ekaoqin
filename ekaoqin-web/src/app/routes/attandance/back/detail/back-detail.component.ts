import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { tap, map } from 'rxjs/operators';
import { STComponent, STColumn, STData } from '@delon/abc';
import { detailData } from './back-detail-mockdata';

@Component({
  selector: 'app-back-detail',
  templateUrl: './back-detail.component.html',
})
export class BackDetailComponent implements OnInit {
  users = detailData;
  q: any = {
    pi: 1,
    ps: 10,
    sorter: '',
    status: null,
    statusList: [],
  };
  data: any[] = [];
  loading = false;
  status = [
    { index: 0, text: '未归', value: false, type: 'error', checked: false },
    { index: 1, text: '正常', value: false, type: 'success', checked: false },
  ];
  userType = [
    { index: 0, text: '管理员' },
    { index: 1, text: '教师' },
    { index: 2, text: '学生' },
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
    { title: '学生姓名', index: 'fullname' },
    { title: '班级', index: 'class' },
    { title: '宿舍', index: 'room' },
    {
      title: '归寝切换',
      buttons: [
        {
          text: '归寝',
          click: (item: any) => this.msg.success(`归寝${item.id}`),
        },
      ],
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
  ) { }

  ngOnInit() {
    if (localStorage.getItem("detailType") == "claxx") {
      console.log(localStorage.getItem("detailClaxx"));
      console.log(localStorage.getItem("detailDate"));
    } else if(localStorage.getItem("detailType") == "room"){
      console.log(localStorage.getItem("detailRoom"));
      console.log(localStorage.getItem("detailDate"));
    }
    this.getData();
  }

  getData() {
    this.data = this.users;
  }

  checkboxChange(list: STData[]) {
    this.selectedRows = list;
    this.totalCallNo = this.selectedRows.reduce(
      (total, cv) => total + cv.callNo,
      0,
    );
  }

  remove() {
    this.http
      .delete('/rule', { nos: this.selectedRows.map(i => i.no).join(',') })
      .subscribe(() => {
        this.getData();
        this.st.clearCheck();
      });
  }

  add(tpl: TemplateRef<{}>) {
    this.modalSrv.create({
      nzTitle: '新建用户',
      nzContent: tpl,
      nzOnOk: () => {
        this.loading = true;
        this.http
          .post('/rule', { description: this.description })
          .subscribe(() => {
            this.getData();
          });
      },
    });
  }

  reset(ls: any[]) {
    for (const item of ls) item.value = false;
    this.getData();
  }
}
