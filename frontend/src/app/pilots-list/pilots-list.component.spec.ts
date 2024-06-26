import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PilotsListComponent } from './pilots-list.component';

describe('PilotsListComponent', () => {
  let component: PilotsListComponent;
  let fixture: ComponentFixture<PilotsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PilotsListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PilotsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
