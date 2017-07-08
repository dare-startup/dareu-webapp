import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminDaresComponent } from './admin-dares.component';

describe('AdminDaresComponent', () => {
  let component: AdminDaresComponent;
  let fixture: ComponentFixture<AdminDaresComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminDaresComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminDaresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
