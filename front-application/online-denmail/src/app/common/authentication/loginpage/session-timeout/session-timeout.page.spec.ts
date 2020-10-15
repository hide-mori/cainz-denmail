import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { SessionTimeoutPage } from './session-timeout.page';

describe('SessionTimeoutPage', () => {
  let component: SessionTimeoutPage;
  let fixture: ComponentFixture<SessionTimeoutPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SessionTimeoutPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(SessionTimeoutPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
