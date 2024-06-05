package cdm.product.common.schedule;

import cdm.base.datetime.AveragingSchedule;
import cdm.base.datetime.DateTimeList;
import cdm.observable.event.MarketDisruptionEnum;
import cdm.observable.event.metafields.FieldWithMetaMarketDisruptionEnum;
import cdm.observable.event.metafields.FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder;
import cdm.product.common.schedule.AveragingObservationList;
import cdm.product.common.schedule.AveragingPeriod;
import cdm.product.common.schedule.AveragingPeriod.AveragingPeriodBuilder;
import cdm.product.common.schedule.AveragingPeriod.AveragingPeriodBuilderImpl;
import cdm.product.common.schedule.AveragingPeriod.AveragingPeriodImpl;
import cdm.product.common.schedule.meta.AveragingPeriodMeta;
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
 * Period over which an average value is taken.
 * @version ${project.version}
 */
@RosettaDataType(value="AveragingPeriod", builder=AveragingPeriod.AveragingPeriodBuilderImpl.class, version="${project.version}")
public interface AveragingPeriod extends RosettaModelObject {

	AveragingPeriodMeta metaData = new AveragingPeriodMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A schedule for generating averaging observation dates.
	 */
	List<? extends AveragingSchedule> getSchedule();
	/**
	 * An unweighted list of averaging observation date and times.
	 */
	DateTimeList getAveragingDateTimes();
	/**
	 * A weighted list of averaging observation date and times.
	 */
	AveragingObservationList getAveragingObservations();
	/**
	 * The market disruption event as defined by ISDA 2002 Definitions.
	 */
	FieldWithMetaMarketDisruptionEnum getMarketDisruption();

	/*********************** Build Methods  ***********************/
	AveragingPeriod build();
	
	AveragingPeriod.AveragingPeriodBuilder toBuilder();
	
	static AveragingPeriod.AveragingPeriodBuilder builder() {
		return new AveragingPeriod.AveragingPeriodBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AveragingPeriod> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AveragingPeriod> getType() {
		return AveragingPeriod.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("schedule"), processor, AveragingSchedule.class, getSchedule());
		processRosetta(path.newSubPath("averagingDateTimes"), processor, DateTimeList.class, getAveragingDateTimes());
		processRosetta(path.newSubPath("averagingObservations"), processor, AveragingObservationList.class, getAveragingObservations());
		processRosetta(path.newSubPath("marketDisruption"), processor, FieldWithMetaMarketDisruptionEnum.class, getMarketDisruption());
	}
	

	/*********************** Builder Interface  ***********************/
	interface AveragingPeriodBuilder extends AveragingPeriod, RosettaModelObjectBuilder {
		AveragingSchedule.AveragingScheduleBuilder getOrCreateSchedule(int _index);
		List<? extends AveragingSchedule.AveragingScheduleBuilder> getSchedule();
		DateTimeList.DateTimeListBuilder getOrCreateAveragingDateTimes();
		DateTimeList.DateTimeListBuilder getAveragingDateTimes();
		AveragingObservationList.AveragingObservationListBuilder getOrCreateAveragingObservations();
		AveragingObservationList.AveragingObservationListBuilder getAveragingObservations();
		FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder getOrCreateMarketDisruption();
		FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder getMarketDisruption();
		AveragingPeriod.AveragingPeriodBuilder addSchedule(AveragingSchedule schedule0);
		AveragingPeriod.AveragingPeriodBuilder addSchedule(AveragingSchedule schedule1, int _idx);
		AveragingPeriod.AveragingPeriodBuilder addSchedule(List<? extends AveragingSchedule> schedule2);
		AveragingPeriod.AveragingPeriodBuilder setSchedule(List<? extends AveragingSchedule> schedule3);
		AveragingPeriod.AveragingPeriodBuilder setAveragingDateTimes(DateTimeList averagingDateTimes);
		AveragingPeriod.AveragingPeriodBuilder setAveragingObservations(AveragingObservationList averagingObservations);
		AveragingPeriod.AveragingPeriodBuilder setMarketDisruption(FieldWithMetaMarketDisruptionEnum marketDisruption0);
		AveragingPeriod.AveragingPeriodBuilder setMarketDisruptionValue(MarketDisruptionEnum marketDisruption1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("schedule"), processor, AveragingSchedule.AveragingScheduleBuilder.class, getSchedule());
			processRosetta(path.newSubPath("averagingDateTimes"), processor, DateTimeList.DateTimeListBuilder.class, getAveragingDateTimes());
			processRosetta(path.newSubPath("averagingObservations"), processor, AveragingObservationList.AveragingObservationListBuilder.class, getAveragingObservations());
			processRosetta(path.newSubPath("marketDisruption"), processor, FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder.class, getMarketDisruption());
		}
		

		AveragingPeriod.AveragingPeriodBuilder prune();
	}

	/*********************** Immutable Implementation of AveragingPeriod  ***********************/
	class AveragingPeriodImpl implements AveragingPeriod {
		private final List<? extends AveragingSchedule> schedule;
		private final DateTimeList averagingDateTimes;
		private final AveragingObservationList averagingObservations;
		private final FieldWithMetaMarketDisruptionEnum marketDisruption;
		
		protected AveragingPeriodImpl(AveragingPeriod.AveragingPeriodBuilder builder) {
			this.schedule = ofNullable(builder.getSchedule()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.averagingDateTimes = ofNullable(builder.getAveragingDateTimes()).map(f->f.build()).orElse(null);
			this.averagingObservations = ofNullable(builder.getAveragingObservations()).map(f->f.build()).orElse(null);
			this.marketDisruption = ofNullable(builder.getMarketDisruption()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("schedule")
		public List<? extends AveragingSchedule> getSchedule() {
			return schedule;
		}
		
		@Override
		@RosettaAttribute("averagingDateTimes")
		public DateTimeList getAveragingDateTimes() {
			return averagingDateTimes;
		}
		
		@Override
		@RosettaAttribute("averagingObservations")
		public AveragingObservationList getAveragingObservations() {
			return averagingObservations;
		}
		
		@Override
		@RosettaAttribute("marketDisruption")
		public FieldWithMetaMarketDisruptionEnum getMarketDisruption() {
			return marketDisruption;
		}
		
		@Override
		public AveragingPeriod build() {
			return this;
		}
		
		@Override
		public AveragingPeriod.AveragingPeriodBuilder toBuilder() {
			AveragingPeriod.AveragingPeriodBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AveragingPeriod.AveragingPeriodBuilder builder) {
			ofNullable(getSchedule()).ifPresent(builder::setSchedule);
			ofNullable(getAveragingDateTimes()).ifPresent(builder::setAveragingDateTimes);
			ofNullable(getAveragingObservations()).ifPresent(builder::setAveragingObservations);
			ofNullable(getMarketDisruption()).ifPresent(builder::setMarketDisruption);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AveragingPeriod _that = getType().cast(o);
		
			if (!ListEquals.listEquals(schedule, _that.getSchedule())) return false;
			if (!Objects.equals(averagingDateTimes, _that.getAveragingDateTimes())) return false;
			if (!Objects.equals(averagingObservations, _that.getAveragingObservations())) return false;
			if (!Objects.equals(marketDisruption, _that.getMarketDisruption())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (schedule != null ? schedule.hashCode() : 0);
			_result = 31 * _result + (averagingDateTimes != null ? averagingDateTimes.hashCode() : 0);
			_result = 31 * _result + (averagingObservations != null ? averagingObservations.hashCode() : 0);
			_result = 31 * _result + (marketDisruption != null ? marketDisruption.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AveragingPeriod {" +
				"schedule=" + this.schedule + ", " +
				"averagingDateTimes=" + this.averagingDateTimes + ", " +
				"averagingObservations=" + this.averagingObservations + ", " +
				"marketDisruption=" + this.marketDisruption +
			'}';
		}
	}

	/*********************** Builder Implementation of AveragingPeriod  ***********************/
	class AveragingPeriodBuilderImpl implements AveragingPeriod.AveragingPeriodBuilder {
	
		protected List<AveragingSchedule.AveragingScheduleBuilder> schedule = new ArrayList<>();
		protected DateTimeList.DateTimeListBuilder averagingDateTimes;
		protected AveragingObservationList.AveragingObservationListBuilder averagingObservations;
		protected FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder marketDisruption;
	
		public AveragingPeriodBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("schedule")
		public List<? extends AveragingSchedule.AveragingScheduleBuilder> getSchedule() {
			return schedule;
		}
		
		public AveragingSchedule.AveragingScheduleBuilder getOrCreateSchedule(int _index) {
		
			if (schedule==null) {
				this.schedule = new ArrayList<>();
			}
			AveragingSchedule.AveragingScheduleBuilder result;
			return getIndex(schedule, _index, () -> {
						AveragingSchedule.AveragingScheduleBuilder newSchedule = AveragingSchedule.builder();
						return newSchedule;
					});
		}
		
		@Override
		@RosettaAttribute("averagingDateTimes")
		public DateTimeList.DateTimeListBuilder getAveragingDateTimes() {
			return averagingDateTimes;
		}
		
		@Override
		public DateTimeList.DateTimeListBuilder getOrCreateAveragingDateTimes() {
			DateTimeList.DateTimeListBuilder result;
			if (averagingDateTimes!=null) {
				result = averagingDateTimes;
			}
			else {
				result = averagingDateTimes = DateTimeList.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("averagingObservations")
		public AveragingObservationList.AveragingObservationListBuilder getAveragingObservations() {
			return averagingObservations;
		}
		
		@Override
		public AveragingObservationList.AveragingObservationListBuilder getOrCreateAveragingObservations() {
			AveragingObservationList.AveragingObservationListBuilder result;
			if (averagingObservations!=null) {
				result = averagingObservations;
			}
			else {
				result = averagingObservations = AveragingObservationList.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("marketDisruption")
		public FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder getMarketDisruption() {
			return marketDisruption;
		}
		
		@Override
		public FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder getOrCreateMarketDisruption() {
			FieldWithMetaMarketDisruptionEnum.FieldWithMetaMarketDisruptionEnumBuilder result;
			if (marketDisruption!=null) {
				result = marketDisruption;
			}
			else {
				result = marketDisruption = FieldWithMetaMarketDisruptionEnum.builder();
			}
			
			return result;
		}
	
		@Override
		public AveragingPeriod.AveragingPeriodBuilder addSchedule(AveragingSchedule schedule) {
			if (schedule!=null) this.schedule.add(schedule.toBuilder());
			return this;
		}
		
		@Override
		public AveragingPeriod.AveragingPeriodBuilder addSchedule(AveragingSchedule schedule, int _idx) {
			getIndex(this.schedule, _idx, () -> schedule.toBuilder());
			return this;
		}
		@Override 
		public AveragingPeriod.AveragingPeriodBuilder addSchedule(List<? extends AveragingSchedule> schedules) {
			if (schedules != null) {
				for (AveragingSchedule toAdd : schedules) {
					this.schedule.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("schedule")
		public AveragingPeriod.AveragingPeriodBuilder setSchedule(List<? extends AveragingSchedule> schedules) {
			if (schedules == null)  {
				this.schedule = new ArrayList<>();
			}
			else {
				this.schedule = schedules.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("averagingDateTimes")
		public AveragingPeriod.AveragingPeriodBuilder setAveragingDateTimes(DateTimeList averagingDateTimes) {
			this.averagingDateTimes = averagingDateTimes==null?null:averagingDateTimes.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("averagingObservations")
		public AveragingPeriod.AveragingPeriodBuilder setAveragingObservations(AveragingObservationList averagingObservations) {
			this.averagingObservations = averagingObservations==null?null:averagingObservations.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("marketDisruption")
		public AveragingPeriod.AveragingPeriodBuilder setMarketDisruption(FieldWithMetaMarketDisruptionEnum marketDisruption) {
			this.marketDisruption = marketDisruption==null?null:marketDisruption.toBuilder();
			return this;
		}
		@Override
		public AveragingPeriod.AveragingPeriodBuilder setMarketDisruptionValue(MarketDisruptionEnum marketDisruption) {
			this.getOrCreateMarketDisruption().setValue(marketDisruption);
			return this;
		}
		
		@Override
		public AveragingPeriod build() {
			return new AveragingPeriod.AveragingPeriodImpl(this);
		}
		
		@Override
		public AveragingPeriod.AveragingPeriodBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AveragingPeriod.AveragingPeriodBuilder prune() {
			schedule = schedule.stream().filter(b->b!=null).<AveragingSchedule.AveragingScheduleBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (averagingDateTimes!=null && !averagingDateTimes.prune().hasData()) averagingDateTimes = null;
			if (averagingObservations!=null && !averagingObservations.prune().hasData()) averagingObservations = null;
			if (marketDisruption!=null && !marketDisruption.prune().hasData()) marketDisruption = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSchedule()!=null && getSchedule().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getAveragingDateTimes()!=null && getAveragingDateTimes().hasData()) return true;
			if (getAveragingObservations()!=null && getAveragingObservations().hasData()) return true;
			if (getMarketDisruption()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AveragingPeriod.AveragingPeriodBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AveragingPeriod.AveragingPeriodBuilder o = (AveragingPeriod.AveragingPeriodBuilder) other;
			
			merger.mergeRosetta(getSchedule(), o.getSchedule(), this::getOrCreateSchedule);
			merger.mergeRosetta(getAveragingDateTimes(), o.getAveragingDateTimes(), this::setAveragingDateTimes);
			merger.mergeRosetta(getAveragingObservations(), o.getAveragingObservations(), this::setAveragingObservations);
			merger.mergeRosetta(getMarketDisruption(), o.getMarketDisruption(), this::setMarketDisruption);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AveragingPeriod _that = getType().cast(o);
		
			if (!ListEquals.listEquals(schedule, _that.getSchedule())) return false;
			if (!Objects.equals(averagingDateTimes, _that.getAveragingDateTimes())) return false;
			if (!Objects.equals(averagingObservations, _that.getAveragingObservations())) return false;
			if (!Objects.equals(marketDisruption, _that.getMarketDisruption())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (schedule != null ? schedule.hashCode() : 0);
			_result = 31 * _result + (averagingDateTimes != null ? averagingDateTimes.hashCode() : 0);
			_result = 31 * _result + (averagingObservations != null ? averagingObservations.hashCode() : 0);
			_result = 31 * _result + (marketDisruption != null ? marketDisruption.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AveragingPeriodBuilder {" +
				"schedule=" + this.schedule + ", " +
				"averagingDateTimes=" + this.averagingDateTimes + ", " +
				"averagingObservations=" + this.averagingObservations + ", " +
				"marketDisruption=" + this.marketDisruption +
			'}';
		}
	}
}
