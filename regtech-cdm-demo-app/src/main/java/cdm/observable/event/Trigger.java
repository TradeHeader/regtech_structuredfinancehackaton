package cdm.observable.event;

import cdm.observable.event.CreditEvents;
import cdm.observable.event.Trigger;
import cdm.observable.event.Trigger.TriggerBuilder;
import cdm.observable.event.Trigger.TriggerBuilderImpl;
import cdm.observable.event.Trigger.TriggerImpl;
import cdm.observable.event.TriggerTimeTypeEnum;
import cdm.observable.event.TriggerTypeEnum;
import cdm.observable.event.meta.TriggerMeta;
import cdm.observable.event.metafields.ReferenceWithMetaCreditEvents;
import cdm.observable.event.metafields.ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Trigger point at which feature is effective.
 * @version ${project.version}
 */
@RosettaDataType(value="Trigger", builder=Trigger.TriggerBuilderImpl.class, version="${project.version}")
public interface Trigger extends RosettaModelObject {

	TriggerMeta metaData = new TriggerMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The trigger level.
	 */
	BigDecimal getLevel();
	/**
	 * The trigger level percentage.
	 */
	BigDecimal getLevelPercentage();
	CreditEvents getCreditEvents();
	ReferenceWithMetaCreditEvents getCreditEventsReference();
	/**
	 * The Triggering condition.
	 */
	TriggerTypeEnum getTriggerType();
	/**
	 * The valuation time type of knock condition.
	 */
	TriggerTimeTypeEnum getTriggerTimeType();

	/*********************** Build Methods  ***********************/
	Trigger build();
	
	Trigger.TriggerBuilder toBuilder();
	
	static Trigger.TriggerBuilder builder() {
		return new Trigger.TriggerBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Trigger> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Trigger> getType() {
		return Trigger.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("level"), BigDecimal.class, getLevel(), this);
		processor.processBasic(path.newSubPath("levelPercentage"), BigDecimal.class, getLevelPercentage(), this);
		processRosetta(path.newSubPath("creditEvents"), processor, CreditEvents.class, getCreditEvents());
		processRosetta(path.newSubPath("creditEventsReference"), processor, ReferenceWithMetaCreditEvents.class, getCreditEventsReference());
		processor.processBasic(path.newSubPath("triggerType"), TriggerTypeEnum.class, getTriggerType(), this);
		processor.processBasic(path.newSubPath("triggerTimeType"), TriggerTimeTypeEnum.class, getTriggerTimeType(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface TriggerBuilder extends Trigger, RosettaModelObjectBuilder {
		CreditEvents.CreditEventsBuilder getOrCreateCreditEvents();
		CreditEvents.CreditEventsBuilder getCreditEvents();
		ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder getOrCreateCreditEventsReference();
		ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder getCreditEventsReference();
		Trigger.TriggerBuilder setLevel(BigDecimal level);
		Trigger.TriggerBuilder setLevelPercentage(BigDecimal levelPercentage);
		Trigger.TriggerBuilder setCreditEvents(CreditEvents creditEvents);
		Trigger.TriggerBuilder setCreditEventsReference(ReferenceWithMetaCreditEvents creditEventsReference0);
		Trigger.TriggerBuilder setCreditEventsReferenceValue(CreditEvents creditEventsReference1);
		Trigger.TriggerBuilder setTriggerType(TriggerTypeEnum triggerType);
		Trigger.TriggerBuilder setTriggerTimeType(TriggerTimeTypeEnum triggerTimeType);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("level"), BigDecimal.class, getLevel(), this);
			processor.processBasic(path.newSubPath("levelPercentage"), BigDecimal.class, getLevelPercentage(), this);
			processRosetta(path.newSubPath("creditEvents"), processor, CreditEvents.CreditEventsBuilder.class, getCreditEvents());
			processRosetta(path.newSubPath("creditEventsReference"), processor, ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder.class, getCreditEventsReference());
			processor.processBasic(path.newSubPath("triggerType"), TriggerTypeEnum.class, getTriggerType(), this);
			processor.processBasic(path.newSubPath("triggerTimeType"), TriggerTimeTypeEnum.class, getTriggerTimeType(), this);
		}
		

		Trigger.TriggerBuilder prune();
	}

	/*********************** Immutable Implementation of Trigger  ***********************/
	class TriggerImpl implements Trigger {
		private final BigDecimal level;
		private final BigDecimal levelPercentage;
		private final CreditEvents creditEvents;
		private final ReferenceWithMetaCreditEvents creditEventsReference;
		private final TriggerTypeEnum triggerType;
		private final TriggerTimeTypeEnum triggerTimeType;
		
		protected TriggerImpl(Trigger.TriggerBuilder builder) {
			this.level = builder.getLevel();
			this.levelPercentage = builder.getLevelPercentage();
			this.creditEvents = ofNullable(builder.getCreditEvents()).map(f->f.build()).orElse(null);
			this.creditEventsReference = ofNullable(builder.getCreditEventsReference()).map(f->f.build()).orElse(null);
			this.triggerType = builder.getTriggerType();
			this.triggerTimeType = builder.getTriggerTimeType();
		}
		
		@Override
		@RosettaAttribute("level")
		public BigDecimal getLevel() {
			return level;
		}
		
		@Override
		@RosettaAttribute("levelPercentage")
		public BigDecimal getLevelPercentage() {
			return levelPercentage;
		}
		
		@Override
		@RosettaAttribute("creditEvents")
		public CreditEvents getCreditEvents() {
			return creditEvents;
		}
		
		@Override
		@RosettaAttribute("creditEventsReference")
		public ReferenceWithMetaCreditEvents getCreditEventsReference() {
			return creditEventsReference;
		}
		
		@Override
		@RosettaAttribute("triggerType")
		public TriggerTypeEnum getTriggerType() {
			return triggerType;
		}
		
		@Override
		@RosettaAttribute("triggerTimeType")
		public TriggerTimeTypeEnum getTriggerTimeType() {
			return triggerTimeType;
		}
		
		@Override
		public Trigger build() {
			return this;
		}
		
		@Override
		public Trigger.TriggerBuilder toBuilder() {
			Trigger.TriggerBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Trigger.TriggerBuilder builder) {
			ofNullable(getLevel()).ifPresent(builder::setLevel);
			ofNullable(getLevelPercentage()).ifPresent(builder::setLevelPercentage);
			ofNullable(getCreditEvents()).ifPresent(builder::setCreditEvents);
			ofNullable(getCreditEventsReference()).ifPresent(builder::setCreditEventsReference);
			ofNullable(getTriggerType()).ifPresent(builder::setTriggerType);
			ofNullable(getTriggerTimeType()).ifPresent(builder::setTriggerTimeType);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Trigger _that = getType().cast(o);
		
			if (!Objects.equals(level, _that.getLevel())) return false;
			if (!Objects.equals(levelPercentage, _that.getLevelPercentage())) return false;
			if (!Objects.equals(creditEvents, _that.getCreditEvents())) return false;
			if (!Objects.equals(creditEventsReference, _that.getCreditEventsReference())) return false;
			if (!Objects.equals(triggerType, _that.getTriggerType())) return false;
			if (!Objects.equals(triggerTimeType, _that.getTriggerTimeType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (level != null ? level.hashCode() : 0);
			_result = 31 * _result + (levelPercentage != null ? levelPercentage.hashCode() : 0);
			_result = 31 * _result + (creditEvents != null ? creditEvents.hashCode() : 0);
			_result = 31 * _result + (creditEventsReference != null ? creditEventsReference.hashCode() : 0);
			_result = 31 * _result + (triggerType != null ? triggerType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (triggerTimeType != null ? triggerTimeType.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Trigger {" +
				"level=" + this.level + ", " +
				"levelPercentage=" + this.levelPercentage + ", " +
				"creditEvents=" + this.creditEvents + ", " +
				"creditEventsReference=" + this.creditEventsReference + ", " +
				"triggerType=" + this.triggerType + ", " +
				"triggerTimeType=" + this.triggerTimeType +
			'}';
		}
	}

	/*********************** Builder Implementation of Trigger  ***********************/
	class TriggerBuilderImpl implements Trigger.TriggerBuilder {
	
		protected BigDecimal level;
		protected BigDecimal levelPercentage;
		protected CreditEvents.CreditEventsBuilder creditEvents;
		protected ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder creditEventsReference;
		protected TriggerTypeEnum triggerType;
		protected TriggerTimeTypeEnum triggerTimeType;
	
		public TriggerBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("level")
		public BigDecimal getLevel() {
			return level;
		}
		
		@Override
		@RosettaAttribute("levelPercentage")
		public BigDecimal getLevelPercentage() {
			return levelPercentage;
		}
		
		@Override
		@RosettaAttribute("creditEvents")
		public CreditEvents.CreditEventsBuilder getCreditEvents() {
			return creditEvents;
		}
		
		@Override
		public CreditEvents.CreditEventsBuilder getOrCreateCreditEvents() {
			CreditEvents.CreditEventsBuilder result;
			if (creditEvents!=null) {
				result = creditEvents;
			}
			else {
				result = creditEvents = CreditEvents.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("creditEventsReference")
		public ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder getCreditEventsReference() {
			return creditEventsReference;
		}
		
		@Override
		public ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder getOrCreateCreditEventsReference() {
			ReferenceWithMetaCreditEvents.ReferenceWithMetaCreditEventsBuilder result;
			if (creditEventsReference!=null) {
				result = creditEventsReference;
			}
			else {
				result = creditEventsReference = ReferenceWithMetaCreditEvents.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("triggerType")
		public TriggerTypeEnum getTriggerType() {
			return triggerType;
		}
		
		@Override
		@RosettaAttribute("triggerTimeType")
		public TriggerTimeTypeEnum getTriggerTimeType() {
			return triggerTimeType;
		}
		
	
		@Override
		@RosettaAttribute("level")
		public Trigger.TriggerBuilder setLevel(BigDecimal level) {
			this.level = level==null?null:level;
			return this;
		}
		@Override
		@RosettaAttribute("levelPercentage")
		public Trigger.TriggerBuilder setLevelPercentage(BigDecimal levelPercentage) {
			this.levelPercentage = levelPercentage==null?null:levelPercentage;
			return this;
		}
		@Override
		@RosettaAttribute("creditEvents")
		public Trigger.TriggerBuilder setCreditEvents(CreditEvents creditEvents) {
			this.creditEvents = creditEvents==null?null:creditEvents.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("creditEventsReference")
		public Trigger.TriggerBuilder setCreditEventsReference(ReferenceWithMetaCreditEvents creditEventsReference) {
			this.creditEventsReference = creditEventsReference==null?null:creditEventsReference.toBuilder();
			return this;
		}
		@Override
		public Trigger.TriggerBuilder setCreditEventsReferenceValue(CreditEvents creditEventsReference) {
			this.getOrCreateCreditEventsReference().setValue(creditEventsReference);
			return this;
		}
		@Override
		@RosettaAttribute("triggerType")
		public Trigger.TriggerBuilder setTriggerType(TriggerTypeEnum triggerType) {
			this.triggerType = triggerType==null?null:triggerType;
			return this;
		}
		@Override
		@RosettaAttribute("triggerTimeType")
		public Trigger.TriggerBuilder setTriggerTimeType(TriggerTimeTypeEnum triggerTimeType) {
			this.triggerTimeType = triggerTimeType==null?null:triggerTimeType;
			return this;
		}
		
		@Override
		public Trigger build() {
			return new Trigger.TriggerImpl(this);
		}
		
		@Override
		public Trigger.TriggerBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Trigger.TriggerBuilder prune() {
			if (creditEvents!=null && !creditEvents.prune().hasData()) creditEvents = null;
			if (creditEventsReference!=null && !creditEventsReference.prune().hasData()) creditEventsReference = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getLevel()!=null) return true;
			if (getLevelPercentage()!=null) return true;
			if (getCreditEvents()!=null && getCreditEvents().hasData()) return true;
			if (getCreditEventsReference()!=null && getCreditEventsReference().hasData()) return true;
			if (getTriggerType()!=null) return true;
			if (getTriggerTimeType()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Trigger.TriggerBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Trigger.TriggerBuilder o = (Trigger.TriggerBuilder) other;
			
			merger.mergeRosetta(getCreditEvents(), o.getCreditEvents(), this::setCreditEvents);
			merger.mergeRosetta(getCreditEventsReference(), o.getCreditEventsReference(), this::setCreditEventsReference);
			
			merger.mergeBasic(getLevel(), o.getLevel(), this::setLevel);
			merger.mergeBasic(getLevelPercentage(), o.getLevelPercentage(), this::setLevelPercentage);
			merger.mergeBasic(getTriggerType(), o.getTriggerType(), this::setTriggerType);
			merger.mergeBasic(getTriggerTimeType(), o.getTriggerTimeType(), this::setTriggerTimeType);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Trigger _that = getType().cast(o);
		
			if (!Objects.equals(level, _that.getLevel())) return false;
			if (!Objects.equals(levelPercentage, _that.getLevelPercentage())) return false;
			if (!Objects.equals(creditEvents, _that.getCreditEvents())) return false;
			if (!Objects.equals(creditEventsReference, _that.getCreditEventsReference())) return false;
			if (!Objects.equals(triggerType, _that.getTriggerType())) return false;
			if (!Objects.equals(triggerTimeType, _that.getTriggerTimeType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (level != null ? level.hashCode() : 0);
			_result = 31 * _result + (levelPercentage != null ? levelPercentage.hashCode() : 0);
			_result = 31 * _result + (creditEvents != null ? creditEvents.hashCode() : 0);
			_result = 31 * _result + (creditEventsReference != null ? creditEventsReference.hashCode() : 0);
			_result = 31 * _result + (triggerType != null ? triggerType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (triggerTimeType != null ? triggerTimeType.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "TriggerBuilder {" +
				"level=" + this.level + ", " +
				"levelPercentage=" + this.levelPercentage + ", " +
				"creditEvents=" + this.creditEvents + ", " +
				"creditEventsReference=" + this.creditEventsReference + ", " +
				"triggerType=" + this.triggerType + ", " +
				"triggerTimeType=" + this.triggerTimeType +
			'}';
		}
	}
}
