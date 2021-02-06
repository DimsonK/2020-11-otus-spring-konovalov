import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit, OnDestroy {

  private subscriptions: Subscription = new Subscription();

  searchForm: FormGroup = this.formBuilder.group({
    searchText: ['', [Validators.required]],
  });

  clickSearch = false;
  clickGetAll = false;
  searchText = '';

  constructor(
    private router: Router,
    private formBuilder: FormBuilder
  ) {
  }

  ngOnInit(): void {
  }

  ngOnDestroy() {
    this.subscriptions.unsubscribe();
  }

  submitSearch(): void {
    if (this.clickSearch && this.searchForm.valid) {
      this.searchText = this.searchForm.get('searchText')?.value;
      this.router.navigate(['/books-view']).then();
    } else if (this.clickGetAll) {
      this.router.navigate(['/books-view']).then();
    }
  }

  public onClickSearchButton(): void {
    this.clickSearch = true;
    this.clickGetAll = false;
  }

  public onClickGetAllButton(): void {
    this.clickSearch = false;
    this.clickGetAll = true;
  }

}
