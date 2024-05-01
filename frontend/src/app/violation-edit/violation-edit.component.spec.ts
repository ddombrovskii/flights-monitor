import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViolationEditComponent } from './violation-edit.component';

describe('ViolationEditComponent', () => {
  let component: ViolationEditComponent;
  let fixture: ComponentFixture<ViolationEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ViolationEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViolationEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
