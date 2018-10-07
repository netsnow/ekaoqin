import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';

@Component({
  selector: 'app-back-room',
  templateUrl: './back-room.component.html',
  styleUrls: ['./back-room.component.less'],
})
export class BackRoomComponent implements OnInit {
  q: any = {
    ps: 8,
    categories: [],
    owners: ['zxx'],
  };

  list: any[] = [];

  loading = true;

  // region: cateogry
  categories = [
    { id: 0, text: '全部', value: false },
    { id: 1, text: '宿舍楼一', value: false },
    { id: 2, text: '宿舍楼二', value: false },
    { id: 3, text: '宿舍楼三', value: false },
    { id: 4, text: '宿舍楼四', value: false },
    { id: 5, text: '宿舍楼五', value: false },
  ];

  changeCategory(status: boolean, idx: number) {
    if (idx === 0) {
      this.categories.map(i => (i.value = status));
    } else {
      this.categories[idx].value = status;
    }
    this.getData();
  }
  // endregion

  constructor(private http: _HttpClient, public msg: NzMessageService) {}

  ngOnInit() {
    this.getData();
  }

  getData() {
    this.loading = true;
    this.http.get('/api/list', { count: this.q.ps }).subscribe((res: any) => {
      this.list = res.map(item => {
        item.activeUser = this.formatWan(item.activeUser);
        return item;
      });
      this.loading = false;
    });
  }

  private formatWan(val) {
    const v = val * 1;
    if (!v || isNaN(v)) return '';

    let result = val;
    if (val > 10000) {
      result = Math.floor(val / 10000);
      result = `${result}`;
    }
    return result;
  }
  showDetail(){
    window.location.href="/#/attandance/back/detail";
  }
}
