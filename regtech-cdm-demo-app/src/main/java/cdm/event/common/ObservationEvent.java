package cdm.event.common;

import cdm.event.common.CorporateAction;
import cdm.event.common.CreditEvent;
import cdm.event.common.ObservationEvent;
import cdm.event.common.ObservationEvent.ObservationEventBuilder;
import cdm.event.common.ObservationEvent.ObservationEventBuilderImpl;
import cdm.event.common.ObservationEvent.ObservationEventImpl;
import cdm.event.common.meta.ObservationEventMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Specifies the necessary information to create any observation event.
 * @version ${project.version}
 */
@RosettaDataType(value="ObservationEvent", builder=ObservationEvent.ObservationEventBuilderImpl.class, version="${project.version}")
public interface ObservationEvent extends RosettaModelObject {

	ObservationEventMeta metaData = new ObservationEventMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the necessary information to create a credit event.
	 */
	CreditEvent getCreditEvent();
	/**
	 * Specifies the necessary information to create a corporate action.
	 */
	CorporateAction getCorporateAction();

	/*********************** Build Methods  ***********************/
	ObservationEvent build();
	
	ObservationEvent.ObservationEventBuilder toBuilder();
	
	static ObservationEvent.ObservationEventBuilder builder() {
		return new ObservationEvent.ObservationEventBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ObservationEvent> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ObservationEvent> getType() {
		return ObservationEvent.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("creditEvent"), processor, CreditEvent.class, getCreditEvent());
		processRosetta(path.newSubPath("corporateAction"), processor, CorporateAction.class, getCorporateAction());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ObservationEventBuilder extends ObservationEvent, RosettaModelObjectBuilder {
		CreditEvent.CreditEventBuilder getOrCreateCreditEvent();
		CreditEvent.CreditEventBuilder getCreditEvent();
		CorporateAction.CorporateActionBuilder getOrCreateCorporateAction();
		CorporateAction.CorporateActionBuilder getCorporateAction();
		ObservationEvent.ObservationEventBuilder setCreditEvent(CreditEvent creditEvent);
		ObservationEvent.ObservationEventBuilder setCorporateAction(CorporateAction corporateAction);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("creditEvent"), processor, CreditEvent.CreditEventBuilder.class, getCreditEvent());
			processRosetta(path.newSubPath("corporateAction"), processor, CorporateAction.CorporateActionBuilder.class, getCorporateAction());
		}
		

		ObservationEvent.ObservationEventBuilder prune();
	}

	/*********************** Immutable Implementation of ObservationEvent  ***********************/
	class ObservationEventImpl implements ObservationEvent {
		private final CreditEvent creditEvent;
		private final CorporateAction corporateAction;
		
		protected ObservationEventImpl(ObservationEvent.ObservationEventBuilder builder) {
			this.creditEvent = ofNullable(builder.getCreditEvent()).map(f->f.build()).orElse(null);
			this.corporateAction = ofNullable(builder.getCorporateAction()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("creditEvent")
		public CreditEvent getCreditEvent() {
			return creditEvent;
		}
		
		@Override
		@RosettaAttribute("corporateAction")
		public CorporateAction getCorporateAction() {
			return corporateAction;
		}
		
		@Override
		public ObservationEvent build() {
			return this;
		}
		
		@Override
		public ObservationEvent.ObservationEventBuilder toBuilder() {
			ObservationEvent.ObservationEventBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ObservationEvent.ObservationEventBuilder builder) {
			ofNullable(getCreditEvent()).ifPresent(builder::setCreditEvent);
			ofNullable(getCorporateAction()).ifPresent(builder::setCorporateAction);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ObservationEvent _that = getType().cast(o);
		
			if (!Objects.equals(creditEvent, _that.getCreditEvent())) return false;
			if (!Objects.equals(corporateAction, _that.getCorporateAction())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (creditEvent != null ? creditEvent.hashCode() : 0);
			_result = 31 * _result + (corporateAction != null ? corporateAction.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ObservationEvent {" +
				"creditEvent=" + this.creditEvent + ", " +
				"corporateAction=" + this.corporateAction +
			'}';
		}
	}

	/*********************** Builder Implementation of ObservationEvent  ***********************/
	class ObservationEventBuilderImpl implements ObservationEvent.ObservationEventBuilder {
	
		protected CreditEvent.CreditEventBuilder creditEvent;
		protected CorporateAction.CorporateActionBuilder corporateAction;
	
		public ObservationEventBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("creditEvent")
		public CreditEvent.CreditEventBuilder getCreditEvent() {
			return creditEvent;
		}
		
		@Override
		public CreditEvent.CreditEventBuilder getOrCreateCreditEvent() {
			CreditEvent.CreditEventBuilder result;
			if (creditEvent!=null) {
				result = creditEvent;
			}
			else {
				result = creditEvent = CreditEvent.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("corporateAction")
		public CorporateAction.CorporateActionBuilder getCorporateAction() {
			return corporateAction;
		}
		
		@Override
		public CorporateAction.CorporateActionBuilder getOrCreateCorporateAction() {
			CorporateAction.CorporateActionBuilder result;
			if (corporateAction!=null) {
				result = corporateAction;
			}
			else {
				result = corporateAction = CorporateAction.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("creditEvent")
		public ObservationEvent.ObservationEventBuilder setCreditEvent(CreditEvent creditEvent) {
			this.creditEvent = creditEvent==null?null:creditEvent.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("corporateAction")
		public ObservationEvent.ObservationEventBuilder setCorporateAction(CorporateAction corporateAction) {
			this.corporateAction = corporateAction==null?null:corporateAction.toBuilder();
			return this;
		}
		
		@Override
		public ObservationEvent build() {
			return new ObservationEvent.ObservationEventImpl(this);
		}
		
		@Override
		public ObservationEvent.ObservationEventBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ObservationEvent.ObservationEventBuilder prune() {
			if (creditEvent!=null && !creditEvent.prune().hasData()) creditEvent = null;
			if (corporateAction!=null && !corporateAction.prune().hasData()) corporateAction = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCreditEvent()!=null && getCreditEvent().hasData()) return true;
			if (getCorporateAction()!=null && getCorporateAction().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ObservationEvent.ObservationEventBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ObservationEvent.ObservationEventBuilder o = (ObservationEvent.ObservationEventBuilder) other;
			
			merger.mergeRosetta(getCreditEvent(), o.getCreditEvent(), this::setCreditEvent);
			merger.mergeRosetta(getCorporateAction(), o.getCorporateAction(), this::setCorporateAction);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ObservationEvent _that = getType().cast(o);
		
			if (!Objects.equals(creditEvent, _that.getCreditEvent())) return false;
			if (!Objects.equals(corporateAction, _that.getCorporateAction())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (creditEvent != null ? creditEvent.hashCode() : 0);
			_result = 31 * _result + (corporateAction != null ? corporateAction.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ObservationEventBuilder {" +
				"creditEvent=" + this.creditEvent + ", " +
				"corporateAction=" + this.corporateAction +
			'}';
		}
	}
}
