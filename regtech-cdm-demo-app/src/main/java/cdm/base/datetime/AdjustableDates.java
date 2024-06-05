package cdm.base.datetime;

import cdm.base.datetime.AdjustableDates;
import cdm.base.datetime.AdjustableDates.AdjustableDatesBuilder;
import cdm.base.datetime.AdjustableDates.AdjustableDatesBuilderImpl;
import cdm.base.datetime.AdjustableDates.AdjustableDatesImpl;
import cdm.base.datetime.BusinessDayAdjustments;
import cdm.base.datetime.meta.AdjustableDatesMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.AttributeMeta;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.FieldWithMetaDate;
import com.rosetta.model.metafields.FieldWithMetaDate.FieldWithMetaDateBuilder;
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A class for defining a series of dates that shall be subject to adjustment if they would otherwise fall on a day that is not a business day in the specified business centers, together with the convention for adjusting the dates.
 * @version ${project.version}
 */
@RosettaDataType(value="AdjustableDates", builder=AdjustableDates.AdjustableDatesBuilderImpl.class, version="${project.version}")
public interface AdjustableDates extends RosettaModelObject, GlobalKey {

	AdjustableDatesMeta metaData = new AdjustableDatesMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A date subject to adjustment.
	 */
	List<Date> getUnadjustedDate();
	/**
	 * The business day convention and financial business centers used for adjusting the date if it would otherwise fall on a day that is not a business date in the specified business centers.
	 */
	BusinessDayAdjustments getDateAdjustments();
	/**
	 * The date(s) once the adjustment has been performed. (Note that this date may change if the business center holidays change).
	 */
	List<? extends FieldWithMetaDate> getAdjustedDate();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	AdjustableDates build();
	
	AdjustableDates.AdjustableDatesBuilder toBuilder();
	
	static AdjustableDates.AdjustableDatesBuilder builder() {
		return new AdjustableDates.AdjustableDatesBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AdjustableDates> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AdjustableDates> getType() {
		return AdjustableDates.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("unadjustedDate"), Date.class, getUnadjustedDate(), this);
		processRosetta(path.newSubPath("dateAdjustments"), processor, BusinessDayAdjustments.class, getDateAdjustments());
		processRosetta(path.newSubPath("adjustedDate"), processor, FieldWithMetaDate.class, getAdjustedDate(), AttributeMeta.GLOBAL_KEY_FIELD);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface AdjustableDatesBuilder extends AdjustableDates, RosettaModelObjectBuilder {
		BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getOrCreateDateAdjustments();
		BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getDateAdjustments();
		FieldWithMetaDate.FieldWithMetaDateBuilder getOrCreateAdjustedDate(int _index);
		List<? extends FieldWithMetaDate.FieldWithMetaDateBuilder> getAdjustedDate();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		AdjustableDates.AdjustableDatesBuilder addUnadjustedDate(Date unadjustedDate0);
		AdjustableDates.AdjustableDatesBuilder addUnadjustedDate(Date unadjustedDate1, int _idx);
		AdjustableDates.AdjustableDatesBuilder addUnadjustedDate(List<? extends Date> unadjustedDate2);
		AdjustableDates.AdjustableDatesBuilder setUnadjustedDate(List<? extends Date> unadjustedDate3);
		AdjustableDates.AdjustableDatesBuilder setDateAdjustments(BusinessDayAdjustments dateAdjustments);
		AdjustableDates.AdjustableDatesBuilder addAdjustedDate(FieldWithMetaDate adjustedDate0);
		AdjustableDates.AdjustableDatesBuilder addAdjustedDate(FieldWithMetaDate adjustedDate1, int _idx);
		AdjustableDates.AdjustableDatesBuilder addAdjustedDateValue(Date adjustedDate2);
		AdjustableDates.AdjustableDatesBuilder addAdjustedDateValue(Date adjustedDate3, int _idx);
		AdjustableDates.AdjustableDatesBuilder addAdjustedDate(List<? extends FieldWithMetaDate> adjustedDate4);
		AdjustableDates.AdjustableDatesBuilder setAdjustedDate(List<? extends FieldWithMetaDate> adjustedDate5);
		AdjustableDates.AdjustableDatesBuilder addAdjustedDateValue(List<? extends Date> adjustedDate6);
		AdjustableDates.AdjustableDatesBuilder setAdjustedDateValue(List<? extends Date> adjustedDate7);
		AdjustableDates.AdjustableDatesBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("unadjustedDate"), Date.class, getUnadjustedDate(), this);
			processRosetta(path.newSubPath("dateAdjustments"), processor, BusinessDayAdjustments.BusinessDayAdjustmentsBuilder.class, getDateAdjustments());
			processRosetta(path.newSubPath("adjustedDate"), processor, FieldWithMetaDate.FieldWithMetaDateBuilder.class, getAdjustedDate(), AttributeMeta.GLOBAL_KEY_FIELD);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		AdjustableDates.AdjustableDatesBuilder prune();
	}

	/*********************** Immutable Implementation of AdjustableDates  ***********************/
	class AdjustableDatesImpl implements AdjustableDates {
		private final List<Date> unadjustedDate;
		private final BusinessDayAdjustments dateAdjustments;
		private final List<? extends FieldWithMetaDate> adjustedDate;
		private final MetaFields meta;
		
		protected AdjustableDatesImpl(AdjustableDates.AdjustableDatesBuilder builder) {
			this.unadjustedDate = ofNullable(builder.getUnadjustedDate()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.dateAdjustments = ofNullable(builder.getDateAdjustments()).map(f->f.build()).orElse(null);
			this.adjustedDate = ofNullable(builder.getAdjustedDate()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("unadjustedDate")
		public List<Date> getUnadjustedDate() {
			return unadjustedDate;
		}
		
		@Override
		@RosettaAttribute("dateAdjustments")
		public BusinessDayAdjustments getDateAdjustments() {
			return dateAdjustments;
		}
		
		@Override
		@RosettaAttribute("adjustedDate")
		public List<? extends FieldWithMetaDate> getAdjustedDate() {
			return adjustedDate;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public AdjustableDates build() {
			return this;
		}
		
		@Override
		public AdjustableDates.AdjustableDatesBuilder toBuilder() {
			AdjustableDates.AdjustableDatesBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AdjustableDates.AdjustableDatesBuilder builder) {
			ofNullable(getUnadjustedDate()).ifPresent(builder::setUnadjustedDate);
			ofNullable(getDateAdjustments()).ifPresent(builder::setDateAdjustments);
			ofNullable(getAdjustedDate()).ifPresent(builder::setAdjustedDate);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AdjustableDates _that = getType().cast(o);
		
			if (!ListEquals.listEquals(unadjustedDate, _that.getUnadjustedDate())) return false;
			if (!Objects.equals(dateAdjustments, _that.getDateAdjustments())) return false;
			if (!ListEquals.listEquals(adjustedDate, _that.getAdjustedDate())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (unadjustedDate != null ? unadjustedDate.hashCode() : 0);
			_result = 31 * _result + (dateAdjustments != null ? dateAdjustments.hashCode() : 0);
			_result = 31 * _result + (adjustedDate != null ? adjustedDate.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AdjustableDates {" +
				"unadjustedDate=" + this.unadjustedDate + ", " +
				"dateAdjustments=" + this.dateAdjustments + ", " +
				"adjustedDate=" + this.adjustedDate + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of AdjustableDates  ***********************/
	class AdjustableDatesBuilderImpl implements AdjustableDates.AdjustableDatesBuilder, GlobalKeyBuilder {
	
		protected List<Date> unadjustedDate = new ArrayList<>();
		protected BusinessDayAdjustments.BusinessDayAdjustmentsBuilder dateAdjustments;
		protected List<FieldWithMetaDate.FieldWithMetaDateBuilder> adjustedDate = new ArrayList<>();
		protected MetaFields.MetaFieldsBuilder meta;
	
		public AdjustableDatesBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("unadjustedDate")
		public List<Date> getUnadjustedDate() {
			return unadjustedDate;
		}
		
		@Override
		@RosettaAttribute("dateAdjustments")
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getDateAdjustments() {
			return dateAdjustments;
		}
		
		@Override
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getOrCreateDateAdjustments() {
			BusinessDayAdjustments.BusinessDayAdjustmentsBuilder result;
			if (dateAdjustments!=null) {
				result = dateAdjustments;
			}
			else {
				result = dateAdjustments = BusinessDayAdjustments.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("adjustedDate")
		public List<? extends FieldWithMetaDate.FieldWithMetaDateBuilder> getAdjustedDate() {
			return adjustedDate;
		}
		
		public FieldWithMetaDate.FieldWithMetaDateBuilder getOrCreateAdjustedDate(int _index) {
		
			if (adjustedDate==null) {
				this.adjustedDate = new ArrayList<>();
			}
			FieldWithMetaDate.FieldWithMetaDateBuilder result;
			return getIndex(adjustedDate, _index, () -> {
						FieldWithMetaDate.FieldWithMetaDateBuilder newAdjustedDate = FieldWithMetaDate.builder();
						return newAdjustedDate;
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
		public AdjustableDates.AdjustableDatesBuilder addUnadjustedDate(Date unadjustedDate) {
			if (unadjustedDate!=null) this.unadjustedDate.add(unadjustedDate);
			return this;
		}
		
		@Override
		public AdjustableDates.AdjustableDatesBuilder addUnadjustedDate(Date unadjustedDate, int _idx) {
			getIndex(this.unadjustedDate, _idx, () -> unadjustedDate);
			return this;
		}
		@Override 
		public AdjustableDates.AdjustableDatesBuilder addUnadjustedDate(List<? extends Date> unadjustedDates) {
			if (unadjustedDates != null) {
				for (Date toAdd : unadjustedDates) {
					this.unadjustedDate.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("unadjustedDate")
		public AdjustableDates.AdjustableDatesBuilder setUnadjustedDate(List<? extends Date> unadjustedDates) {
			if (unadjustedDates == null)  {
				this.unadjustedDate = new ArrayList<>();
			}
			else {
				this.unadjustedDate = unadjustedDates.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("dateAdjustments")
		public AdjustableDates.AdjustableDatesBuilder setDateAdjustments(BusinessDayAdjustments dateAdjustments) {
			this.dateAdjustments = dateAdjustments==null?null:dateAdjustments.toBuilder();
			return this;
		}
		@Override
		public AdjustableDates.AdjustableDatesBuilder addAdjustedDate(FieldWithMetaDate adjustedDate) {
			if (adjustedDate!=null) this.adjustedDate.add(adjustedDate.toBuilder());
			return this;
		}
		
		@Override
		public AdjustableDates.AdjustableDatesBuilder addAdjustedDate(FieldWithMetaDate adjustedDate, int _idx) {
			getIndex(this.adjustedDate, _idx, () -> adjustedDate.toBuilder());
			return this;
		}
		
		@Override
		public AdjustableDates.AdjustableDatesBuilder addAdjustedDateValue(Date adjustedDate) {
			this.getOrCreateAdjustedDate(-1).setValue(adjustedDate);
			return this;
		}
		
		@Override
		public AdjustableDates.AdjustableDatesBuilder addAdjustedDateValue(Date adjustedDate, int _idx) {
			this.getOrCreateAdjustedDate(_idx).setValue(adjustedDate);
			return this;
		}
		@Override 
		public AdjustableDates.AdjustableDatesBuilder addAdjustedDate(List<? extends FieldWithMetaDate> adjustedDates) {
			if (adjustedDates != null) {
				for (FieldWithMetaDate toAdd : adjustedDates) {
					this.adjustedDate.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("adjustedDate")
		public AdjustableDates.AdjustableDatesBuilder setAdjustedDate(List<? extends FieldWithMetaDate> adjustedDates) {
			if (adjustedDates == null)  {
				this.adjustedDate = new ArrayList<>();
			}
			else {
				this.adjustedDate = adjustedDates.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public AdjustableDates.AdjustableDatesBuilder addAdjustedDateValue(List<? extends Date> adjustedDates) {
			if (adjustedDates != null) {
				for (Date toAdd : adjustedDates) {
					this.addAdjustedDateValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public AdjustableDates.AdjustableDatesBuilder setAdjustedDateValue(List<? extends Date> adjustedDates) {
			this.adjustedDate.clear();
			if (adjustedDates!=null) {
				adjustedDates.forEach(this::addAdjustedDateValue);
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("meta")
		public AdjustableDates.AdjustableDatesBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public AdjustableDates build() {
			return new AdjustableDates.AdjustableDatesImpl(this);
		}
		
		@Override
		public AdjustableDates.AdjustableDatesBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AdjustableDates.AdjustableDatesBuilder prune() {
			if (dateAdjustments!=null && !dateAdjustments.prune().hasData()) dateAdjustments = null;
			adjustedDate = adjustedDate.stream().filter(b->b!=null).<FieldWithMetaDate.FieldWithMetaDateBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getUnadjustedDate()!=null && !getUnadjustedDate().isEmpty()) return true;
			if (getDateAdjustments()!=null && getDateAdjustments().hasData()) return true;
			if (getAdjustedDate()!=null && !getAdjustedDate().isEmpty()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AdjustableDates.AdjustableDatesBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AdjustableDates.AdjustableDatesBuilder o = (AdjustableDates.AdjustableDatesBuilder) other;
			
			merger.mergeRosetta(getDateAdjustments(), o.getDateAdjustments(), this::setDateAdjustments);
			merger.mergeRosetta(getAdjustedDate(), o.getAdjustedDate(), this::getOrCreateAdjustedDate);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getUnadjustedDate(), o.getUnadjustedDate(), (Consumer<Date>) this::addUnadjustedDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AdjustableDates _that = getType().cast(o);
		
			if (!ListEquals.listEquals(unadjustedDate, _that.getUnadjustedDate())) return false;
			if (!Objects.equals(dateAdjustments, _that.getDateAdjustments())) return false;
			if (!ListEquals.listEquals(adjustedDate, _that.getAdjustedDate())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (unadjustedDate != null ? unadjustedDate.hashCode() : 0);
			_result = 31 * _result + (dateAdjustments != null ? dateAdjustments.hashCode() : 0);
			_result = 31 * _result + (adjustedDate != null ? adjustedDate.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AdjustableDatesBuilder {" +
				"unadjustedDate=" + this.unadjustedDate + ", " +
				"dateAdjustments=" + this.dateAdjustments + ", " +
				"adjustedDate=" + this.adjustedDate + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
