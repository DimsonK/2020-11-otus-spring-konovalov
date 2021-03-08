import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  constructor(
    private router: Router,
    private auth: AuthService
  ) { }

  ngOnInit(): void {
  }

  submit() {
    if (this.auth.isLoggedIn()) {
      this.router.navigate(['/manage']).then();
    } else {
      this.router.navigate(['/login']).then();
    }

  }
}
