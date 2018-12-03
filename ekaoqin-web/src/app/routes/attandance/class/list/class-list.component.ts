import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { tap, map } from 'rxjs/operators';
import { STComponent, STColumn, STData } from '@delon/abc';
import { classData } from './class-mockdata';
import { ClaxxService } from '../../common/service/claxx.service';

@Component({
  selector: 'app-class-list',
  templateUrl: './class-list.component.html',
})
export class ClassListComponent implements OnInit {
  claxxInfo = {};
  classes = classData;
  modalClassname = "";
  modalClassStatus = "";
  q: any = {
    pi: 1,
    ps: 10,
    sorter: '',
    status: null,
    statusList: [],
  };
  data: any = {};
  loading = false;
  status = [
    { index: 0, text: '已毕业', value: false, type: 'error', checked: false },
    { index: 1, text: '正常', value: false, type: 'success', checked: false },
  ];

  @ViewChild('st')
  st: STComponent;
  columns: STColumn[] = [
    { title: '', index: 'key', type: 'checkbox' },
    { title: '班级名', index: 'name' },
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
    private classService: ClaxxService,

  ) { }

  ngOnInit() {
    this.getData();
  }

  getData() {
    this.loading = true;
    this.classService.getAllClaxxes(this.claxxInfo).subscribe(
      resp => {
        this.loading = false;
        this.data = resp;
        //console.log(resp);

      },
      error => {
        this.loading = false;
        this.msg.error(error.error.message);
        //console.log(error);
      }
    )
  }

  checkboxChange(list: STData[]) {
    this.selectedRows = list;
    this.totalCallNo = this.selectedRows.reduce(
      (total, cv) => total + cv.callNo,
      0,
    );
  }

  remove(id) {
    this.classService.remove(id).subscribe(
      resp => {
        this.msg.success("已删除！");
        this.getData();
      },
      error => {
        this.msg.error("删除失败！");
        console.log(error)
      }
    )
  }

  add(tpl: TemplateRef<{}>) {
    this.modalClassname = '';
    this.modalClassStatus = '';
    this.modalSrv.create({
      nzTitle: '新建班级',
      nzContent: tpl,
      nzOnOk: () => {
        this.loading = true;
        var classInfo = {};
        classInfo["name"] = this.modalClassname;
        this.classService.saveClaxx(classInfo)
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
  edit(tpl: TemplateRef<{}>, id, classname, classstatus) {
    this.modalClassname = classname;
    this.modalClassStatus = classstatus;

    this.modalSrv.create({
      nzTitle: '编辑班级',
      nzContent: tpl,
      nzOnOk: () => {
        this.loading = true;
        var classInfo = {};
        classInfo["id"] = id;
        classInfo["name"] = this.modalClassname;
        console.log(classInfo);
        this.classService.editClaxx(id, classInfo)
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
    this.claxxInfo = {};
    this.getData();
  }
}
