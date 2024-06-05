package cdm.product.template;

import cdm.product.template.CalculationSchedule;
import cdm.product.template.CalculationSchedule.CalculationScheduleBuilder;
import cdm.product.template.CalculationSchedule.CalculationScheduleBuilderImpl;
import cdm.product.template.CalculationSchedule.CalculationScheduleImpl;
import cdm.product.template.SchedulePeriod;
import cdm.product.template.meta.CalculationScheduleMeta;
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
 * A class that allows the full representation of a payout by defining a set of schedule periods. It supports standard schedule customization by expressing all the dates, quantities, and pricing data in a non-parametric way.
 * @version ${project.version}
 */
@RosettaDataType(value="CalculationSchedule", builder=CalculationSchedule.CalculationScheduleBuilderImpl.class, version="${project.version}")
public interface CalculationSchedule extends RosettaModelObject {

	CalculationScheduleMeta metaData = new CalculationScheduleMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Defines a period of a calculation schedule structure.
	 */
	List<? extends SchedulePeriod> getSchedulePeriod();

	/*********************** Build Methods  ***********************/
	CalculationSchedule build();
	
	CalculationSchedule.CalculationScheduleBuilder toBuilder();
	
	static CalculationSchedule.CalculationScheduleBuilder builder() {
		return new CalculationSchedule.CalculationScheduleBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CalculationSchedule> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CalculationSchedule> getType() {
		return CalculationSchedule.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("schedulePeriod"), processor, SchedulePeriod.class, getSchedulePeriod());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CalculationScheduleBuilder extends CalculationSchedule, RosettaModelObjectBuilder {
		SchedulePeriod.SchedulePeriodBuilder getOrCreateSchedulePeriod(int _index);
		List<? extends SchedulePeriod.SchedulePeriodBuilder> getSchedulePeriod();
		CalculationSchedule.CalculationScheduleBuilder addSchedulePeriod(SchedulePeriod schedulePeriod0);
		CalculationSchedule.CalculationScheduleBuilder addSchedulePeriod(SchedulePeriod schedulePeriod1, int _idx);
		CalculationSchedule.CalculationScheduleBuilder addSchedulePeriod(List<? extends SchedulePeriod> schedulePeriod2);
		CalculationSchedule.CalculationScheduleBuilder setSchedulePeriod(List<? extends SchedulePeriod> schedulePeriod3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("schedulePeriod"), processor, SchedulePeriod.SchedulePeriodBuilder.class, getSchedulePeriod());
		}
		

		CalculationSchedule.CalculationScheduleBuilder prune();
	}

	/*********************** Immutable Implementation of CalculationSchedule  ***********************/
	class CalculationScheduleImpl implements CalculationSchedule {
		private final List<? extends SchedulePeriod> schedulePeriod;
		
		protected CalculationScheduleImpl(CalculationSchedule.CalculationScheduleBuilder builder) {
			this.schedulePeriod = ofNullable(builder.getSchedulePeriod()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("schedulePeriod")
		public List<? extends SchedulePeriod> getSchedulePeriod() {
			return schedulePeriod;
		}
		
		@Override
		public CalculationSchedule build() {
			return this;
		}
		
		@Override
		public CalculationSchedule.CalculationScheduleBuilder toBuilder() {
			CalculationSchedule.CalculationScheduleBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CalculationSchedule.CalculationScheduleBuilder builder) {
			ofNullable(getSchedulePeriod()).ifPresent(builder::setSchedulePeriod);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CalculationSchedule _that = getType().cast(o);
		
			if (!ListEquals.listEquals(schedulePeriod, _that.getSchedulePeriod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (schedulePeriod != null ? schedulePeriod.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalculationSchedule {" +
				"schedulePeriod=" + this.schedulePeriod +
			'}';
		}
	}

	/*********************** Builder Implementation of CalculationSchedule  ***********************/
	class CalculationScheduleBuilderImpl implements CalculationSchedule.CalculationScheduleBuilder {
	
		protected List<SchedulePeriod.SchedulePeriodBuilder> schedulePeriod = new ArrayList<>();
	
		public CalculationScheduleBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("schedulePeriod")
		public List<? extends SchedulePeriod.SchedulePeriodBuilder> getSchedulePeriod() {
			return schedulePeriod;
		}
		
		public SchedulePeriod.SchedulePeriodBuilder getOrCreateSchedulePeriod(int _index) {
		
			if (schedulePeriod==null) {
				this.schedulePeriod = new ArrayList<>();
			}
			SchedulePeriod.SchedulePeriodBuilder result;
			return getIndex(schedulePeriod, _index, () -> {
						SchedulePeriod.SchedulePeriodBuilder newSchedulePeriod = SchedulePeriod.builder();
						return newSchedulePeriod;
					});
		}
		
	
		@Override
		public CalculationSchedule.CalculationScheduleBuilder addSchedulePeriod(SchedulePeriod schedulePeriod) {
			if (schedulePeriod!=null) this.schedulePeriod.add(schedulePeriod.toBuilder());
			return this;
		}
		
		@Override
		public CalculationSchedule.CalculationScheduleBuilder addSchedulePeriod(SchedulePeriod schedulePeriod, int _idx) {
			getIndex(this.schedulePeriod, _idx, () -> schedulePeriod.toBuilder());
			return this;
		}
		@Override 
		public CalculationSchedule.CalculationScheduleBuilder addSchedulePeriod(List<? extends SchedulePeriod> schedulePeriods) {
			if (schedulePeriods != null) {
				for (SchedulePeriod toAdd : schedulePeriods) {
					this.schedulePeriod.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("schedulePeriod")
		public CalculationSchedule.CalculationScheduleBuilder setSchedulePeriod(List<? extends SchedulePeriod> schedulePeriods) {
			if (schedulePeriods == null)  {
				this.schedulePeriod = new ArrayList<>();
			}
			else {
				this.schedulePeriod = schedulePeriods.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public CalculationSchedule build() {
			return new CalculationSchedule.CalculationScheduleImpl(this);
		}
		
		@Override
		public CalculationSchedule.CalculationScheduleBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalculationSchedule.CalculationScheduleBuilder prune() {
			schedulePeriod = schedulePeriod.stream().filter(b->b!=null).<SchedulePeriod.SchedulePeriodBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSchedulePeriod()!=null && getSchedulePeriod().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalculationSchedule.CalculationScheduleBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CalculationSchedule.CalculationScheduleBuilder o = (CalculationSchedule.CalculationScheduleBuilder) other;
			
			merger.mergeRosetta(getSchedulePeriod(), o.getSchedulePeriod(), this::getOrCreateSchedulePeriod);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CalculationSchedule _that = getType().cast(o);
		
			if (!ListEquals.listEquals(schedulePeriod, _that.getSchedulePeriod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (schedulePeriod != null ? schedulePeriod.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalculationScheduleBuilder {" +
				"schedulePeriod=" + this.schedulePeriod +
			'}';
		}
	}
}
