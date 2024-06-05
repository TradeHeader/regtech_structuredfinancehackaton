package cdm.event.workflow;

import cdm.base.staticdata.identifier.IdentifiedList;
import cdm.event.common.CorporateActionTypeEnum;
import cdm.event.common.EventIntentEnum;
import cdm.event.common.Instruction;
import cdm.event.workflow.EventInstruction;
import cdm.event.workflow.EventInstruction.EventInstructionBuilder;
import cdm.event.workflow.EventInstruction.EventInstructionBuilderImpl;
import cdm.event.workflow.EventInstruction.EventInstructionImpl;
import cdm.event.workflow.meta.EventInstructionMeta;
import com.google.common.collect.ImmutableList;
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
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Specifies instructions to create a BusinessEvent.
 * @version ${project.version}
 */
@RosettaDataType(value="EventInstruction", builder=EventInstruction.EventInstructionBuilderImpl.class, version="${project.version}")
public interface EventInstruction extends RosettaModelObject {

	EventInstructionMeta metaData = new EventInstructionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The intent attribute is meant to be specified when the event qualification cannot be programmatically inferred from the event features. As a result it is only associated with those primitives that can give way to such ambiguity, the quantityChange being one of those. An example of such is a reduction in the trade notional, which could be interpreted as either a trade correction (unless a maximum period of time post-event is specified as part of the qualification), a partial termination or a portfolio rebalancing in the case of an equity swap. On the other hand, an event such as the exercise is not expected to have an associated intent as there should not be ambiguity.
	 */
	EventIntentEnum getIntent();
	CorporateActionTypeEnum getCorporateActionIntent();
	/**
	 * Specifies the date on which the event is taking place. This date is equal to the trade date in the case of a simple execution.  However it can be different from the trade date, for example in the case of a partial termination.
	 */
	Date getEventDate();
	/**
	 * The date on which the event contractually takes effect, when different from the event date.
	 */
	Date getEffectiveDate();
	/**
	 * Specifies the package information in case the business event represents several trades executed as a package (hence this attribute is optional). The package information is only instantiated once at the business event level to preserve referential integrity, whereas individual trades make reference to it to identify that they are part of a package.
	 */
	IdentifiedList getPackageInformation();
	/**
	 * Specifies the instructions to create the Business Event.
	 */
	List<? extends Instruction> getInstruction();

	/*********************** Build Methods  ***********************/
	EventInstruction build();
	
	EventInstruction.EventInstructionBuilder toBuilder();
	
	static EventInstruction.EventInstructionBuilder builder() {
		return new EventInstruction.EventInstructionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends EventInstruction> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends EventInstruction> getType() {
		return EventInstruction.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("intent"), EventIntentEnum.class, getIntent(), this);
		processor.processBasic(path.newSubPath("corporateActionIntent"), CorporateActionTypeEnum.class, getCorporateActionIntent(), this);
		processor.processBasic(path.newSubPath("eventDate"), Date.class, getEventDate(), this);
		processor.processBasic(path.newSubPath("effectiveDate"), Date.class, getEffectiveDate(), this);
		processRosetta(path.newSubPath("packageInformation"), processor, IdentifiedList.class, getPackageInformation());
		processRosetta(path.newSubPath("instruction"), processor, Instruction.class, getInstruction());
	}
	

	/*********************** Builder Interface  ***********************/
	interface EventInstructionBuilder extends EventInstruction, RosettaModelObjectBuilder {
		IdentifiedList.IdentifiedListBuilder getOrCreatePackageInformation();
		IdentifiedList.IdentifiedListBuilder getPackageInformation();
		Instruction.InstructionBuilder getOrCreateInstruction(int _index);
		List<? extends Instruction.InstructionBuilder> getInstruction();
		EventInstruction.EventInstructionBuilder setIntent(EventIntentEnum intent);
		EventInstruction.EventInstructionBuilder setCorporateActionIntent(CorporateActionTypeEnum corporateActionIntent);
		EventInstruction.EventInstructionBuilder setEventDate(Date eventDate);
		EventInstruction.EventInstructionBuilder setEffectiveDate(Date effectiveDate);
		EventInstruction.EventInstructionBuilder setPackageInformation(IdentifiedList packageInformation);
		EventInstruction.EventInstructionBuilder addInstruction(Instruction instruction0);
		EventInstruction.EventInstructionBuilder addInstruction(Instruction instruction1, int _idx);
		EventInstruction.EventInstructionBuilder addInstruction(List<? extends Instruction> instruction2);
		EventInstruction.EventInstructionBuilder setInstruction(List<? extends Instruction> instruction3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("intent"), EventIntentEnum.class, getIntent(), this);
			processor.processBasic(path.newSubPath("corporateActionIntent"), CorporateActionTypeEnum.class, getCorporateActionIntent(), this);
			processor.processBasic(path.newSubPath("eventDate"), Date.class, getEventDate(), this);
			processor.processBasic(path.newSubPath("effectiveDate"), Date.class, getEffectiveDate(), this);
			processRosetta(path.newSubPath("packageInformation"), processor, IdentifiedList.IdentifiedListBuilder.class, getPackageInformation());
			processRosetta(path.newSubPath("instruction"), processor, Instruction.InstructionBuilder.class, getInstruction());
		}
		

		EventInstruction.EventInstructionBuilder prune();
	}

	/*********************** Immutable Implementation of EventInstruction  ***********************/
	class EventInstructionImpl implements EventInstruction {
		private final EventIntentEnum intent;
		private final CorporateActionTypeEnum corporateActionIntent;
		private final Date eventDate;
		private final Date effectiveDate;
		private final IdentifiedList packageInformation;
		private final List<? extends Instruction> instruction;
		
		protected EventInstructionImpl(EventInstruction.EventInstructionBuilder builder) {
			this.intent = builder.getIntent();
			this.corporateActionIntent = builder.getCorporateActionIntent();
			this.eventDate = builder.getEventDate();
			this.effectiveDate = builder.getEffectiveDate();
			this.packageInformation = ofNullable(builder.getPackageInformation()).map(f->f.build()).orElse(null);
			this.instruction = ofNullable(builder.getInstruction()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("intent")
		public EventIntentEnum getIntent() {
			return intent;
		}
		
		@Override
		@RosettaAttribute("corporateActionIntent")
		public CorporateActionTypeEnum getCorporateActionIntent() {
			return corporateActionIntent;
		}
		
		@Override
		@RosettaAttribute("eventDate")
		public Date getEventDate() {
			return eventDate;
		}
		
		@Override
		@RosettaAttribute("effectiveDate")
		public Date getEffectiveDate() {
			return effectiveDate;
		}
		
		@Override
		@RosettaAttribute("packageInformation")
		public IdentifiedList getPackageInformation() {
			return packageInformation;
		}
		
		@Override
		@RosettaAttribute("instruction")
		public List<? extends Instruction> getInstruction() {
			return instruction;
		}
		
		@Override
		public EventInstruction build() {
			return this;
		}
		
		@Override
		public EventInstruction.EventInstructionBuilder toBuilder() {
			EventInstruction.EventInstructionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(EventInstruction.EventInstructionBuilder builder) {
			ofNullable(getIntent()).ifPresent(builder::setIntent);
			ofNullable(getCorporateActionIntent()).ifPresent(builder::setCorporateActionIntent);
			ofNullable(getEventDate()).ifPresent(builder::setEventDate);
			ofNullable(getEffectiveDate()).ifPresent(builder::setEffectiveDate);
			ofNullable(getPackageInformation()).ifPresent(builder::setPackageInformation);
			ofNullable(getInstruction()).ifPresent(builder::setInstruction);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			EventInstruction _that = getType().cast(o);
		
			if (!Objects.equals(intent, _that.getIntent())) return false;
			if (!Objects.equals(corporateActionIntent, _that.getCorporateActionIntent())) return false;
			if (!Objects.equals(eventDate, _that.getEventDate())) return false;
			if (!Objects.equals(effectiveDate, _that.getEffectiveDate())) return false;
			if (!Objects.equals(packageInformation, _that.getPackageInformation())) return false;
			if (!ListEquals.listEquals(instruction, _that.getInstruction())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (intent != null ? intent.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (corporateActionIntent != null ? corporateActionIntent.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (eventDate != null ? eventDate.hashCode() : 0);
			_result = 31 * _result + (effectiveDate != null ? effectiveDate.hashCode() : 0);
			_result = 31 * _result + (packageInformation != null ? packageInformation.hashCode() : 0);
			_result = 31 * _result + (instruction != null ? instruction.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "EventInstruction {" +
				"intent=" + this.intent + ", " +
				"corporateActionIntent=" + this.corporateActionIntent + ", " +
				"eventDate=" + this.eventDate + ", " +
				"effectiveDate=" + this.effectiveDate + ", " +
				"packageInformation=" + this.packageInformation + ", " +
				"instruction=" + this.instruction +
			'}';
		}
	}

	/*********************** Builder Implementation of EventInstruction  ***********************/
	class EventInstructionBuilderImpl implements EventInstruction.EventInstructionBuilder {
	
		protected EventIntentEnum intent;
		protected CorporateActionTypeEnum corporateActionIntent;
		protected Date eventDate;
		protected Date effectiveDate;
		protected IdentifiedList.IdentifiedListBuilder packageInformation;
		protected List<Instruction.InstructionBuilder> instruction = new ArrayList<>();
	
		public EventInstructionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("intent")
		public EventIntentEnum getIntent() {
			return intent;
		}
		
		@Override
		@RosettaAttribute("corporateActionIntent")
		public CorporateActionTypeEnum getCorporateActionIntent() {
			return corporateActionIntent;
		}
		
		@Override
		@RosettaAttribute("eventDate")
		public Date getEventDate() {
			return eventDate;
		}
		
		@Override
		@RosettaAttribute("effectiveDate")
		public Date getEffectiveDate() {
			return effectiveDate;
		}
		
		@Override
		@RosettaAttribute("packageInformation")
		public IdentifiedList.IdentifiedListBuilder getPackageInformation() {
			return packageInformation;
		}
		
		@Override
		public IdentifiedList.IdentifiedListBuilder getOrCreatePackageInformation() {
			IdentifiedList.IdentifiedListBuilder result;
			if (packageInformation!=null) {
				result = packageInformation;
			}
			else {
				result = packageInformation = IdentifiedList.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("instruction")
		public List<? extends Instruction.InstructionBuilder> getInstruction() {
			return instruction;
		}
		
		public Instruction.InstructionBuilder getOrCreateInstruction(int _index) {
		
			if (instruction==null) {
				this.instruction = new ArrayList<>();
			}
			Instruction.InstructionBuilder result;
			return getIndex(instruction, _index, () -> {
						Instruction.InstructionBuilder newInstruction = Instruction.builder();
						return newInstruction;
					});
		}
		
	
		@Override
		@RosettaAttribute("intent")
		public EventInstruction.EventInstructionBuilder setIntent(EventIntentEnum intent) {
			this.intent = intent==null?null:intent;
			return this;
		}
		@Override
		@RosettaAttribute("corporateActionIntent")
		public EventInstruction.EventInstructionBuilder setCorporateActionIntent(CorporateActionTypeEnum corporateActionIntent) {
			this.corporateActionIntent = corporateActionIntent==null?null:corporateActionIntent;
			return this;
		}
		@Override
		@RosettaAttribute("eventDate")
		public EventInstruction.EventInstructionBuilder setEventDate(Date eventDate) {
			this.eventDate = eventDate==null?null:eventDate;
			return this;
		}
		@Override
		@RosettaAttribute("effectiveDate")
		public EventInstruction.EventInstructionBuilder setEffectiveDate(Date effectiveDate) {
			this.effectiveDate = effectiveDate==null?null:effectiveDate;
			return this;
		}
		@Override
		@RosettaAttribute("packageInformation")
		public EventInstruction.EventInstructionBuilder setPackageInformation(IdentifiedList packageInformation) {
			this.packageInformation = packageInformation==null?null:packageInformation.toBuilder();
			return this;
		}
		@Override
		public EventInstruction.EventInstructionBuilder addInstruction(Instruction instruction) {
			if (instruction!=null) this.instruction.add(instruction.toBuilder());
			return this;
		}
		
		@Override
		public EventInstruction.EventInstructionBuilder addInstruction(Instruction instruction, int _idx) {
			getIndex(this.instruction, _idx, () -> instruction.toBuilder());
			return this;
		}
		@Override 
		public EventInstruction.EventInstructionBuilder addInstruction(List<? extends Instruction> instructions) {
			if (instructions != null) {
				for (Instruction toAdd : instructions) {
					this.instruction.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("instruction")
		public EventInstruction.EventInstructionBuilder setInstruction(List<? extends Instruction> instructions) {
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
		public EventInstruction build() {
			return new EventInstruction.EventInstructionImpl(this);
		}
		
		@Override
		public EventInstruction.EventInstructionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EventInstruction.EventInstructionBuilder prune() {
			if (packageInformation!=null && !packageInformation.prune().hasData()) packageInformation = null;
			instruction = instruction.stream().filter(b->b!=null).<Instruction.InstructionBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getIntent()!=null) return true;
			if (getCorporateActionIntent()!=null) return true;
			if (getEventDate()!=null) return true;
			if (getEffectiveDate()!=null) return true;
			if (getPackageInformation()!=null && getPackageInformation().hasData()) return true;
			if (getInstruction()!=null && getInstruction().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EventInstruction.EventInstructionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			EventInstruction.EventInstructionBuilder o = (EventInstruction.EventInstructionBuilder) other;
			
			merger.mergeRosetta(getPackageInformation(), o.getPackageInformation(), this::setPackageInformation);
			merger.mergeRosetta(getInstruction(), o.getInstruction(), this::getOrCreateInstruction);
			
			merger.mergeBasic(getIntent(), o.getIntent(), this::setIntent);
			merger.mergeBasic(getCorporateActionIntent(), o.getCorporateActionIntent(), this::setCorporateActionIntent);
			merger.mergeBasic(getEventDate(), o.getEventDate(), this::setEventDate);
			merger.mergeBasic(getEffectiveDate(), o.getEffectiveDate(), this::setEffectiveDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			EventInstruction _that = getType().cast(o);
		
			if (!Objects.equals(intent, _that.getIntent())) return false;
			if (!Objects.equals(corporateActionIntent, _that.getCorporateActionIntent())) return false;
			if (!Objects.equals(eventDate, _that.getEventDate())) return false;
			if (!Objects.equals(effectiveDate, _that.getEffectiveDate())) return false;
			if (!Objects.equals(packageInformation, _that.getPackageInformation())) return false;
			if (!ListEquals.listEquals(instruction, _that.getInstruction())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (intent != null ? intent.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (corporateActionIntent != null ? corporateActionIntent.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (eventDate != null ? eventDate.hashCode() : 0);
			_result = 31 * _result + (effectiveDate != null ? effectiveDate.hashCode() : 0);
			_result = 31 * _result + (packageInformation != null ? packageInformation.hashCode() : 0);
			_result = 31 * _result + (instruction != null ? instruction.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "EventInstructionBuilder {" +
				"intent=" + this.intent + ", " +
				"corporateActionIntent=" + this.corporateActionIntent + ", " +
				"eventDate=" + this.eventDate + ", " +
				"effectiveDate=" + this.effectiveDate + ", " +
				"packageInformation=" + this.packageInformation + ", " +
				"instruction=" + this.instruction +
			'}';
		}
	}
}
