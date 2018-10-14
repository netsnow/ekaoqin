import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { tap, map } from 'rxjs/operators';
import { STComponent, STColumn, STData } from '@delon/abc';
import { roomData } from './room-mockdata';
import { RoomService } from '../../common/service/room.service';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
})
export class RoomListComponent implements OnInit {
  rooms = roomData;
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
    { title: '', index: 'key', type: 'checkbox' },
    { title: '房间名', index: 'name' },
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
    private roomService: RoomService,
  ) { }

  ngOnInit() {
    this.getData();
  }

  getData() {
    this.loading = true;
    this.roomService.getAllRooms().subscribe(
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

  remove(id) {
    this.roomService.remove(id).subscribe(
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
    this.modalRoomname = '';
    this.modalSrv.create({
      nzTitle: '新建班级',
      nzContent: tpl,
      nzOnOk: () => {
        this.loading = true;
        var roomInfo = {};
        roomInfo["name"] = this.modalRoomname;
        this.roomService.saveRoom(roomInfo)
          .subscribe(
            resp => {
              this.loading = false;
              this.msg.success("已保存！");
              this.getData();
            },
            error => {
              this.loading = false;
              this.msg.error("保存失败！");
              console.log(error);
            }
          );
      },
    });
  }
  edit(tpl: TemplateRef<{}>, id, roomname) {
    this.modalRoomname = roomname;

    this.modalSrv.create({
      nzTitle: '编辑班级',
      nzContent: tpl,
      nzOnOk: () => {
        this.loading = true;
        var roomInfo = {};
        roomInfo["id"] = id;
        roomInfo["name"] = this.modalRoomname;
        console.log(roomInfo);
        this.roomService.editRoom(id, roomInfo)
          .subscribe(
            resp => {
              this.loading = false;
              this.msg.success("已保存！");
              this.getData();
            },
            error => {
              this.loading = false;
              this.msg.error("保存失败！");
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
