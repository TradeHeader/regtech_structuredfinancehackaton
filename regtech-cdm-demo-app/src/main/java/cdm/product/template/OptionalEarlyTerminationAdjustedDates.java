package cdm.product.template;

import cdm.product.template.EarlyTerminationEvent;
import cdm.product.template.OptionalEarlyTerminationAdjustedDates;
import cdm.product.template.OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder;
import cdm.product.template.OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilderImpl;
import cdm.product.template.OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesImpl;
import cdm.product.template.meta.OptionalEarlyTerminationAdjustedDatesMeta;
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
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A data defining:  the adjusted dates associated with an optional early termination provision.
 * @version ${project.version}
 */
@RosettaDataType(value="OptionalEarlyTerminationAdjustedDates", builder=OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilderImpl.class, version="${project.version}")
public interface OptionalEarlyTerminationAdjustedDates extends RosettaModelObject {

	OptionalEarlyTerminationAdjustedDatesMeta metaData = new OptionalEarlyTerminationAdjustedDatesMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The adjusted dates associated with an individual early termination date.
	 */
	List<? extends EarlyTerminationEvent> getEarlyTerminationEvent();

	/*********************** Build Methods  ***********************/
	OptionalEarlyTerminationAdjustedDates build();
	
	OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder toBuilder();
	
	static OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder builder() {
		return new OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends OptionalEarlyTerminationAdjustedDates> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends OptionalEarlyTerminationAdjustedDates> getType() {
		return OptionalEarlyTerminationAdjustedDates.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("earlyTerminationEvent"), processor, EarlyTerminationEvent.class, getEarlyTerminationEvent());
	}
	

	/*********************** Builder Interface  ***********************/
	interface OptionalEarlyTerminationAdjustedDatesBuilder extends OptionalEarlyTerminationAdjustedDates, RosettaModelObjectBuilder {
		EarlyTerminationEvent.EarlyTerminationEventBuilder getOrCreateEarlyTerminationEvent(int _index);
		List<? extends EarlyTerminationEvent.EarlyTerminationEventBuilder> getEarlyTerminationEvent();
		OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder addEarlyTerminationEvent(EarlyTerminationEvent earlyTerminationEvent0);
		OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder addEarlyTerminationEvent(EarlyTerminationEvent earlyTerminationEvent1, int _idx);
		OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder addEarlyTerminationEvent(List<? extends EarlyTerminationEvent> earlyTerminationEvent2);
		OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder setEarlyTerminationEvent(List<? extends EarlyTerminationEvent> earlyTerminationEvent3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("earlyTerminationEvent"), processor, EarlyTerminationEvent.EarlyTerminationEventBuilder.class, getEarlyTerminationEvent());
		}
		

		OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder prune();
	}

	/*********************** Immutable Implementation of OptionalEarlyTerminationAdjustedDates  ***********************/
	class OptionalEarlyTerminationAdjustedDatesImpl implements OptionalEarlyTerminationAdjustedDates {
		private final List<? extends EarlyTerminationEvent> earlyTerminationEvent;
		
		protected OptionalEarlyTerminationAdjustedDatesImpl(OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder builder) {
			this.earlyTerminationEvent = ofNullable(builder.getEarlyTerminationEvent()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("earlyTerminationEvent")
		public List<? extends EarlyTerminationEvent> getEarlyTerminationEvent() {
			return earlyTerminationEvent;
		}
		
		@Override
		public OptionalEarlyTerminationAdjustedDates build() {
			return this;
		}
		
		@Override
		public OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder toBuilder() {
			OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder builder) {
			ofNullable(getEarlyTerminationEvent()).ifPresent(builder::setEarlyTerminationEvent);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			OptionalEarlyTerminationAdjustedDates _that = getType().cast(o);
		
			if (!ListEquals.listEquals(earlyTerminationEvent, _that.getEarlyTerminationEvent())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (earlyTerminationEvent != null ? earlyTerminationEvent.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "OptionalEarlyTerminationAdjustedDates {" +
				"earlyTerminationEvent=" + this.earlyTerminationEvent +
			'}';
		}
	}

	/*********************** Builder Implementation of OptionalEarlyTerminationAdjustedDates  ***********************/
	class OptionalEarlyTerminationAdjustedDatesBuilderImpl implements OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder {
	
		protected List<EarlyTerminationEvent.EarlyTerminationEventBuilder> earlyTerminationEvent = new ArrayList<>();
	
		public OptionalEarlyTerminationAdjustedDatesBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("earlyTerminationEvent")
		public List<? extends EarlyTerminationEvent.EarlyTerminationEventBuilder> getEarlyTerminationEvent() {
			return earlyTerminationEvent;
		}
		
		public EarlyTerminationEvent.EarlyTerminationEventBuilder getOrCreateEarlyTerminationEvent(int _index) {
		
			if (earlyTerminationEvent==null) {
				this.earlyTerminationEvent = new ArrayList<>();
			}
			EarlyTerminationEvent.EarlyTerminationEventBuilder result;
			return getIndex(earlyTerminationEvent, _index, () -> {
						EarlyTerminationEvent.EarlyTerminationEventBuilder newEarlyTerminationEvent = EarlyTerminationEvent.builder();
						return newEarlyTerminationEvent;
					});
		}
		
	
		@Override
		public OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder addEarlyTerminationEvent(EarlyTerminationEvent earlyTerminationEvent) {
			if (earlyTerminationEvent!=null) this.earlyTerminationEvent.add(earlyTerminationEvent.toBuilder());
			return this;
		}
		
		@Override
		public OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder addEarlyTerminationEvent(EarlyTerminationEvent earlyTerminationEvent, int _idx) {
			getIndex(this.earlyTerminationEvent, _idx, () -> earlyTerminationEvent.toBuilder());
			return this;
		}
		@Override 
		public OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder addEarlyTerminationEvent(List<? extends EarlyTerminationEvent> earlyTerminationEvents) {
			if (earlyTerminationEvents != null) {
				for (EarlyTerminationEvent toAdd : earlyTerminationEvents) {
					this.earlyTerminationEvent.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("earlyTerminationEvent")
		public OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder setEarlyTerminationEvent(List<? extends EarlyTerminationEvent> earlyTerminationEvents) {
			if (earlyTerminationEvents == null)  {
				this.earlyTerminationEvent = new ArrayList<>();
			}
			else {
				this.earlyTerminationEvent = earlyTerminationEvents.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public OptionalEarlyTerminationAdjustedDates build() {
			return new OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesImpl(this);
		}
		
		@Override
		public OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder prune() {
			earlyTerminationEvent = earlyTerminationEvent.stream().filter(b->b!=null).<EarlyTerminationEvent.EarlyTerminationEventBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getEarlyTerminationEvent()!=null && getEarlyTerminationEvent().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder o = (OptionalEarlyTerminationAdjustedDates.OptionalEarlyTerminationAdjustedDatesBuilder) other;
			
			merger.mergeRosetta(getEarlyTerminationEvent(), o.getEarlyTerminationEvent(), this::getOrCreateEarlyTerminationEvent);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			OptionalEarlyTerminationAdjustedDates _that = getType().cast(o);
		
			if (!ListEquals.listEquals(earlyTerminationEvent, _that.getEarlyTerminationEvent())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (earlyTerminationEvent != null ? earlyTerminationEvent.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "OptionalEarlyTerminationAdjustedDatesBuilder {" +
				"earlyTerminationEvent=" + this.earlyTerminationEvent +
			'}';
		}
	}
}
