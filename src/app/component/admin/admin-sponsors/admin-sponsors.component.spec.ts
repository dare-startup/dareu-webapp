import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSponsorsComponent } from './admin-sponsors.component';

describe('AdminSponsorsComponent', () => {
  let component: AdminSponsorsComponent;
  let fixture: ComponentFixture<AdminSponsorsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminSponsorsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminSponsorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
