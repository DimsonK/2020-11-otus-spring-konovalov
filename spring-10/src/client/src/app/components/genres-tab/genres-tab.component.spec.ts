import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenresTabComponent } from './genres-tab.component';

describe('GenresTabComponent', () => {
  let component: GenresTabComponent;
  let fixture: ComponentFixture<GenresTabComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GenresTabComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GenresTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
