import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { backData } from '../back-mockdata';

@Component({
  selector: 'app-back-class',
  templateUrl: './back-class.component.html',
  styleUrls: ['./back-class.component.less'],
})
export class BackClassComponent implements OnInit {
  q: any = {
    ps: 8,
    categories: [],
    owners: ['zxx'],
  };
  data = backData;
  list: any[] = [];

  loading = false;

  // region: cateogry
  categories = [
    { id: 0, text: '全部', value: false },
    { id: 1, text: '2016级', value: false },
    { id: 2, text: '2017级', value: false },
    { id: 3, text: '2018级', value: false },
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

  constructor(private http: _HttpClient, public msg: NzMessageService) { }

  ngOnInit() {
    this.getData();
  }

  getData() {
    this.list = this.data;
  }

  showDetail() {
    window.location.href = "/#/attandance/back/detail";
  }
}
