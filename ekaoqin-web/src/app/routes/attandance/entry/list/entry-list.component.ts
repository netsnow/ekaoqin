import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { tap, map } from 'rxjs/operators';
import { STComponent, STColumn, STData } from '@delon/abc';
import { EntryService } from '../../common/service/entry.service';

@Component({
  selector: 'app-entry-list',
  templateUrl: './entry-list.component.html',
})
export class EntryListComponent implements OnInit {
  rooms = "";
  modalRoomname = "";
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
    { index: 0, text: '已废弃', value: false, type: 'error', checked: false },
    { index: 1, text: '正常', value: false, type: 'success', checked: false },
  ];

  @ViewChild('st')
  st: STComponent;
  columns: STColumn[] = [
    { title: '学生姓名', index: 'studentName' },
    { title: '学生班级', index: 'claxxName' },
    { title: '学生宿舍', index: 'roomName' },
    { title: '拍摄机位', index: 'cameraId' },
    { title: '拍摄时间', index: 'dateTime' },
  ];
  selectedRows: STData[] = [];
  description = '';
  totalCallNo = 0;
  expandForm = false;

  constructor(
    private http: _HttpClient,
    public msg: NzMessageService,
    private modalSrv: NzModalService,
    private entryService: EntryService,
  ) { }

  ngOnInit() {
    this.getData();
  }

  getData() {
    this.loading = true;
    this.entryService.getAllEntryLogs().subscribe(
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
  }

  checkboxChange(list: STData[]) {
    this.selectedRows = list;
    this.totalCallNo = this.selectedRows.reduce(
      (total, cv) => total + cv.callNo,
      0,
    );
  }

  reset(ls: any[]) {
    for (const item of ls) item.value = false;
    this.getData();
  }
}
