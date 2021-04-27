import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NGXLogger } from 'ngx-logger';
import { MessageService } from 'primeng/api';
import { Subscription } from 'rxjs';
import { AuthRequest, AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {
  private subscriptions: Subscription = new Subscription();

  target: string | null = null;

  credentials: AuthRequest = {
    userName: '',
    password: '',
  };

  submitBtnDisable = true;

  constructor(
    private auth: AuthService,
    private logger: NGXLogger,
    private router: Router,
    private route: ActivatedRoute,
    private messageService: MessageService,
    private fb: FormBuilder
  ) {
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.target = params['target'];
    });
  }

  ngOnDestroy() {
    this.subscriptions.unsubscribe();
  }

  loginForm: FormGroup = this.fb.group({
    userName: ['', [Validators.required]],
    userPassword: ['', [Validators.required]],
  });

  login() {
    this.subscriptions.add(
      this.auth.login(this.credentials).subscribe(
        () => {
          this.logger.info(`login page target : ${this.target}`);
          this.router.navigate([this.target ? this.target : '/home']).then();
        },
        (err) => {
          this.logger.error(err.message);
        }
      )
    );
  }

  submit() {
    if (this.loginForm.valid) {
      this.credentials.userName = this.loginForm.get('userName')?.value;
      this.credentials.password = this.loginForm.get('userPassword')?.value;
      this.login();
    }
  }


}
