import { SettingsService, MenuService } from '@delon/theme';
import { Component, OnDestroy, Inject, Optional } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import {
  SocialService,
  SocialOpenType,
  TokenService,
  DA_SERVICE_TOKEN,
} from '@delon/auth';
import { ACLService } from '@delon/acl';
import { ReuseTabService } from '@delon/abc';
import { environment } from '@env/environment';
import { StartupService } from '@core/startup/startup.service';
import { AuthService } from '../../attandance/common/service/auth.service';
import { UserService } from '../../attandance/common/service/user.service';

@Component({
  selector: 'passport-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less'],
  providers: [SocialService],
})
export class UserLoginComponent implements OnDestroy {
  form: FormGroup;
  error = '';
  type = 0;
  loading = false;
  user = {};

  constructor(
    fb: FormBuilder,
    private router: Router,
    public msg: NzMessageService,
    private modalSrv: NzModalService,
    private settingsService: SettingsService,
    private socialService: SocialService,
    @Optional()
    @Inject(ReuseTabService)
    private reuseTabService: ReuseTabService,
    @Inject(DA_SERVICE_TOKEN) private tokenService: TokenService,
    private startupSrv: StartupService,
    private authService: AuthService,
    private userService: UserService,
    private aclService: ACLService,
    private settingService: SettingsService,
    private menuService: MenuService,
  ) {
    this.form = fb.group({
      userName: [null, [Validators.required, Validators.minLength(5)]],
      password: [null, Validators.required],
    });
    modalSrv.closeAll();
  }

  // region: fields

  get userName() {
    return this.form.controls.userName;
  }
  get password() {
    return this.form.controls.password;
  }


  // endregion

  submit() {
    this.error = '';
    if (this.type === 0) {
      this.userName.markAsDirty();
      this.userName.updateValueAndValidity();
      this.password.markAsDirty();
      this.password.updateValueAndValidity();
      if (this.userName.invalid || this.password.invalid) return;
    } else {

    }
    this.loading = true;
    var loginInfo = {
      username: this.userName.value,
      password: this.password.value,
    }
    this.authService.login(loginInfo)
      .subscribe(
        resp => {
          this.loading = false;
          //console.log(resp["token"]);
          // 清空路由复用信息
          this.reuseTabService.clear();
          // 设置Token信息
          this.tokenService.set({
            token: resp["token"],
            name: this.userName.value,
            //email: `cipchk@qq.com`,
            //id: 10000,
            time: +new Date(),
          });
          this.aclService.setFull(false);
          this.getMe();


        },
        error => {
          this.loading = false;
          this.error = `账户或密码错误`;
          console.log(error);
        });
  }

  getMe() {
    this.userService.getMe().subscribe(
      resp => {
        //console.log(resp)
        this.user["name"] = resp["username"];
        this.user["avatar"] = "./assets/tmp/img/avatar.jpg";
        this.user["authorities"] = resp["authorities"];
        this.settingService.setUser(this.user);
        var authorities = this.settingService.user["authorities"];
        var roles = [];
        for (var i = 0; i < authorities.length; i++) {
          roles.push(authorities[i].authority)
        }
        this.aclService.setRole(roles)
        this.menuService.resume();
        this.router.navigate(['/']);
      },
      error => {
        this.loading = false;
        this.error = `用户信息不完整`;
        //console.log(error);
      }
    )

  }
  ngOnDestroy(): void {

  }
}
