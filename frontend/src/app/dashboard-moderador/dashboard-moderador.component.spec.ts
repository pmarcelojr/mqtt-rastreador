import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardModeradorComponent } from './dashboard-moderador.component';

describe('DashboardModeradorComponent', () => {
  let component: DashboardModeradorComponent;
  let fixture: ComponentFixture<DashboardModeradorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashboardModeradorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardModeradorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
