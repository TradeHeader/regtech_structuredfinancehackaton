package cdm.event.common;

import cdm.base.staticdata.identifier.IdentifiedList;
import cdm.event.common.BusinessEvent;
import cdm.event.common.BusinessEvent.BusinessEventBuilder;
import cdm.event.common.BusinessEvent.BusinessEventBuilderImpl;
import cdm.event.common.BusinessEvent.BusinessEventImpl;
import cdm.event.common.CorporateActionTypeEnum;
import cdm.event.common.EventIntentEnum;
import cdm.event.common.Instruction;
import cdm.event.common.TradeState;
import cdm.event.common.meta.BusinessEventMeta;
import cdm.event.workflow.EventInstruction;
import cdm.event.workflow.EventInstruction.EventInstructionBuilder;
import cdm.event.workflow.EventInstruction.EventInstructionBuilderImpl;
import cdm.event.workflow.EventInstruction.EventInstructionImpl;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A business event represents a life cycle event of a trade. The combination of the state changes results in a qualifiable life cycle event. An example of a Business Event is a PartialTermination which is a defined by a quantity change primitive event.
 * @version ${project.version}
 */
@RosettaDataType(value="BusinessEvent", builder=BusinessEvent.BusinessEventBuilderImpl.class, version="${project.version}")
public interface BusinessEvent extends EventInstruction, GlobalKey {

	BusinessEventMeta metaData = new BusinessEventMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The CDM event qualifier, which corresponds to the outcome of the isEvent qualification logic which qualifies the lifecycle event as a function of its features (e.g. PartialTermination, ClearingSubmission, Novation, ...).
	 */
	String getEventQualifier();
	/**
	 * Specifies the after trade state(s) created.
	 */
	List<? extends TradeState> getAfter();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	BusinessEvent build();
	
	BusinessEvent.BusinessEventBuilder toBuilder();
	
	static BusinessEvent.BusinessEventBuilder builder() {
		return new BusinessEvent.BusinessEventBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends BusinessEvent> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends BusinessEvent> getType() {
		return BusinessEvent.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("intent"), EventIntentEnum.class, getIntent(), this);
		processor.processBasic(path.newSubPath("corporateActionIntent"), CorporateActionTypeEnum.class, getCorporateActionIntent(), this);
		processor.processBasic(path.newSubPath("eventDate"), Date.class, getEventDate(), this);
		processor.processBasic(path.newSubPath("effectiveDate"), Date.class, getEffectiveDate(), this);
		processRosetta(path.newSubPath("packageInformation"), processor, IdentifiedList.class, getPackageInformation());
		processRosetta(path.newSubPath("instruction"), processor, Instruction.class, getInstruction());
		processor.processBasic(path.newSubPath("eventQualifier"), String.class, getEventQualifier(), this);
		processRosetta(path.newSubPath("after"), processor, TradeState.class, getAfter());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface BusinessEventBuilder extends BusinessEvent, EventInstruction.EventInstructionBuilder, RosettaModelObjectBuilder {
		TradeState.TradeStateBuilder getOrCreateAfter(int _index);
		List<? extends TradeState.TradeStateBuilder> getAfter();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		BusinessEvent.BusinessEventBuilder setIntent(EventIntentEnum intent);
		BusinessEvent.BusinessEventBuilder setCorporateActionIntent(CorporateActionTypeEnum corporateActionIntent);
		BusinessEvent.BusinessEventBuilder setEventDate(Date eventDate);
		BusinessEvent.BusinessEventBuilder setEffectiveDate(Date effectiveDate);
		BusinessEvent.BusinessEventBuilder setPackageInformation(IdentifiedList packageInformation);
		BusinessEvent.BusinessEventBuilder addInstruction(Instruction instruction0);
		BusinessEvent.BusinessEventBuilder addInstruction(Instruction instruction1, int _idx);
		BusinessEvent.BusinessEventBuilder addInstruction(List<? extends Instruction> instruction2);
		BusinessEvent.BusinessEventBuilder setInstruction(List<? extends Instruction> instruction3);
		BusinessEvent.BusinessEventBuilder setEventQualifier(String eventQualifier);
		BusinessEvent.BusinessEventBuilder addAfter(TradeState after0);
		BusinessEvent.BusinessEventBuilder addAfter(TradeState after1, int _idx);
		BusinessEvent.BusinessEventBuilder addAfter(List<? extends TradeState> after2);
		BusinessEvent.BusinessEventBuilder setAfter(List<? extends TradeState> after3);
		BusinessEvent.BusinessEventBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("intent"), EventIntentEnum.class, getIntent(), this);
			processor.processBasic(path.newSubPath("corporateActionIntent"), CorporateActionTypeEnum.class, getCorporateActionIntent(), this);
			processor.processBasic(path.newSubPath("eventDate"), Date.class, getEventDate(), this);
			processor.processBasic(path.newSubPath("effectiveDate"), Date.class, getEffectiveDate(), this);
			processRosetta(path.newSubPath("packageInformation"), processor, IdentifiedList.IdentifiedListBuilder.class, getPackageInformation());
			processRosetta(path.newSubPath("instruction"), processor, Instruction.InstructionBuilder.class, getInstruction());
			processor.processBasic(path.newSubPath("eventQualifier"), String.class, getEventQualifier(), this);
			processRosetta(path.newSubPath("after"), processor, TradeState.TradeStateBuilder.class, getAfter());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		BusinessEvent.BusinessEventBuilder prune();
	}

	/*********************** Immutable Implementation of BusinessEvent  ***********************/
	class BusinessEventImpl extends EventInstruction.EventInstructionImpl implements BusinessEvent {
		private final String eventQualifier;
		private final List<? extends TradeState> after;
		private final MetaFields meta;
		
		protected BusinessEventImpl(BusinessEvent.BusinessEventBuilder builder) {
			super(builder);
			this.eventQualifier = builder.getEventQualifier();
			this.after = ofNullable(builder.getAfter()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("eventQualifier")
		public String getEventQualifier() {
			return eventQualifier;
		}
		
		@Override
		@RosettaAttribute("after")
		public List<? extends TradeState> getAfter() {
			return after;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public BusinessEvent build() {
			return this;
		}
		
		@Override
		public BusinessEvent.BusinessEventBuilder toBuilder() {
			BusinessEvent.BusinessEventBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(BusinessEvent.BusinessEventBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getEventQualifier()).ifPresent(builder::setEventQualifier);
			ofNullable(getAfter()).ifPresent(builder::setAfter);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			BusinessEvent _that = getType().cast(o);
		
			if (!Objects.equals(eventQualifier, _that.getEventQualifier())) return false;
			if (!ListEquals.listEquals(after, _that.getAfter())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (eventQualifier != null ? eventQualifier.hashCode() : 0);
			_result = 31 * _result + (after != null ? after.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BusinessEvent {" +
				"eventQualifier=" + this.eventQualifier + ", " +
				"after=" + this.after + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of BusinessEvent  ***********************/
	class BusinessEventBuilderImpl extends EventInstruction.EventInstructionBuilderImpl  implements BusinessEvent.BusinessEventBuilder, GlobalKeyBuilder {
	
		protected String eventQualifier;
		protected List<TradeState.TradeStateBuilder> after = new ArrayList<>();
		protected MetaFields.MetaFieldsBuilder meta;
	
		public BusinessEventBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("eventQualifier")
		public String getEventQualifier() {
			return eventQualifier;
		}
		
		@Override
		@RosettaAttribute("after")
		public List<? extends TradeState.TradeStateBuilder> getAfter() {
			return after;
		}
		
		public TradeState.TradeStateBuilder getOrCreateAfter(int _index) {
		
			if (after==null) {
				this.after = new ArrayList<>();
			}
			TradeState.TradeStateBuilder result;
			return getIndex(after, _index, () -> {
						TradeState.TradeStateBuilder newAfter = TradeState.builder();
						return newAfter;
					});
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("intent")
		public BusinessEvent.BusinessEventBuilder setIntent(EventIntentEnum intent) {
			this.intent = intent==null?null:intent;
			return this;
		}
		@Override
		@RosettaAttribute("corporateActionIntent")
		public BusinessEvent.BusinessEventBuilder setCorporateActionIntent(CorporateActionTypeEnum corporateActionIntent) {
			this.corporateActionIntent = corporateActionIntent==null?null:corporateActionIntent;
			return this;
		}
		@Override
		@RosettaAttribute("eventDate")
		public BusinessEvent.BusinessEventBuilder setEventDate(Date eventDate) {
			this.eventDate = eventDate==null?null:eventDate;
			return this;
		}
		@Override
		@RosettaAttribute("effectiveDate")
		public BusinessEvent.BusinessEventBuilder setEffectiveDate(Date effectiveDate) {
			this.effectiveDate = effectiveDate==null?null:effectiveDate;
			return this;
		}
		@Override
		@RosettaAttribute("packageInformation")
		public BusinessEvent.BusinessEventBuilder setPackageInformation(IdentifiedList packageInformation) {
			this.packageInformation = packageInformation==null?null:packageInformation.toBuilder();
			return this;
		}
		@Override
		public BusinessEvent.BusinessEventBuilder addInstruction(Instruction instruction) {
			if (instruction!=null) this.instruction.add(instruction.toBuilder());
			return this;
		}
		
		@Override
		public BusinessEvent.BusinessEventBuilder addInstruction(Instruction instruction, int _idx) {
			getIndex(this.instruction, _idx, () -> instruction.toBuilder());
			return this;
		}
		@Override 
		public BusinessEvent.BusinessEventBuilder addInstruction(List<? extends Instruction> instructions) {
			if (instructions != null) {
				for (Instruction toAdd : instructions) {
					this.instruction.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("instruction")
		public BusinessEvent.BusinessEventBuilder setInstruction(List<? extends Instruction> instructions) {
			if (instructions == null)  {
				this.instruction = new ArrayList<>();
			}
			else {
				this.instruction = instructions.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("eventQualifier")
		public BusinessEvent.BusinessEventBuilder setEventQualifier(String eventQualifier) {
			this.eventQualifier = eventQualifier==null?null:eventQualifier;
			return this;
		}
		@Override
		public BusinessEvent.BusinessEventBuilder addAfter(TradeState after) {
			if (after!=null) this.after.add(after.toBuilder());
			return this;
		}
		
		@Override
		public BusinessEvent.BusinessEventBuilder addAfter(TradeState after, int _idx) {
			getIndex(this.after, _idx, () -> after.toBuilder());
			return this;
		}
		@Override 
		public BusinessEvent.BusinessEventBuilder addAfter(List<? extends TradeState> afters) {
			if (afters != null) {
				for (TradeState toAdd : afters) {
					this.after.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("after")
		public BusinessEvent.BusinessEventBuilder setAfter(List<? extends TradeState> afters) {
			if (afters == null)  {
				this.after = new ArrayList<>();
			}
			else {
				this.after = afters.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("meta")
		public BusinessEvent.BusinessEventBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public BusinessEvent build() {
			return new BusinessEvent.BusinessEventImpl(this);
		}
		
		@Override
		public BusinessEvent.BusinessEventBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BusinessEvent.BusinessEventBuilder prune() {
			super.prune();
			after = after.stream().filter(b->b!=null).<TradeState.TradeStateBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getEventQualifier()!=null) return true;
			if (getAfter()!=null && getAfter().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BusinessEvent.BusinessEventBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			BusinessEvent.BusinessEventBuilder o = (BusinessEvent.BusinessEventBuilder) other;
			
			merger.mergeRosetta(getAfter(), o.getAfter(), this::getOrCreateAfter);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getEventQualifier(), o.getEventQualifier(), this::setEventQualifier);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			BusinessEvent _that = getType().cast(o);
		
			if (!Objects.equals(eventQualifier, _that.getEventQualifier())) return false;
			if (!ListEquals.listEquals(after, _that.getAfter())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (eventQualifier != null ? eventQualifier.hashCode() : 0);
			_result = 31 * _result + (after != null ? after.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BusinessEventBuilder {" +
				"eventQualifier=" + this.eventQualifier + ", " +
				"after=" + this.after + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}
}
