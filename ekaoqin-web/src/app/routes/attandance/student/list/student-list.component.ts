import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { tap, map } from 'rxjs/operators';
import { STComponent, STColumn, STData } from '@delon/abc';
import { StudentService } from '../../common/service/student.service';
import { ClaxxService } from '../../common/service/claxx.service';
import { RoomService } from '../../common/service/room.service';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
})
export class StudentListComponent implements OnInit {
  rooms = '';
  modalStudentname = "";
  modalClaxxId = "";
  modalRoomId = "";
  modalCardId = "";
  q: any = {
    pi: 1,
    ps: 10,
    sorter: '',
    status: null,
    statusList: [],
  };
  data: any = {};
  classList: any = {};
  roomList: any = {};
  loading = false;
  status = [
    { index: 0, text: '已废弃', value: false, type: 'error', checked: false },
    { index: 1, text: '正常', value: false, type: 'success', checked: false },
  ];

  @ViewChild('st')
  st: STComponent;
  columns: STColumn[] = [
    { title: '', index: 'key', type: 'checkbox' },
    { title: '学生名', index: 'name' },
    { title: '班级', index: 'claxxName' },
    { title: '宿舍', index: 'roomName' },
    { title: '归寝状态', index: 'backStatus', type: 'yn' },
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
    private studentService: StudentService,
    private claxxService: ClaxxService,
    private roomService: RoomService,
  ) { }

  ngOnInit() {
    this.getClaxx();
    this.getRoom();
    this.getData();
  }

  getData() {
    this.loading = true;
    this.studentService.getAllStudents().subscribe(
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
  getClaxx() {
    this.claxxService.getAllClaxxes().subscribe(
      resp => {
        this.classList = resp;
        console.log(resp);

      },
      error => {
        console.log(error);
      }
    )
  }
  getRoom() {
    this.roomService.getAllRooms().subscribe(
      resp => {
        this.roomList = resp;
        console.log(resp);
      },
      error => {
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
    this.studentService.remove(id).subscribe(
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
    this.modalStudentname = "";
    this.modalClaxxId = "";
    this.modalRoomId = "";
    this.modalCardId = "";
    this.modalSrv.create({
      nzTitle: '新建学生',
      nzContent: tpl,
      nzOnOk: () => {
        this.loading = true;
        var studentInfo = {};
        studentInfo["name"] = this.modalStudentname;
        studentInfo["classId"] = this.modalClaxxId;
        studentInfo["roomId"] = this.modalRoomId;
        studentInfo["faceSysUserId"] = this.modalCardId;
        this.studentService.saveStudent(studentInfo)
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
  edit(tpl: TemplateRef<{}>, student) {
    this.modalStudentname = student.name;
    this.modalClaxxId = student.classId;
    this.modalRoomId = student.roomId;
    this.modalCardId = student.faceSysUserId;;
    this.modalSrv.create({
      nzTitle: '编辑班级',
      nzContent: tpl,
      nzOnOk: () => {
        this.loading = true;
        var studentInfo = {};
        studentInfo["id"] = student.id;
        studentInfo["name"] = this.modalStudentname;
        studentInfo["classId"] = this.modalClaxxId;
        studentInfo["roomId"] = this.modalRoomId;
        studentInfo["faceSysUserId"] = this.modalCardId;
        this.studentService.editStudent(student.id, studentInfo)
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
