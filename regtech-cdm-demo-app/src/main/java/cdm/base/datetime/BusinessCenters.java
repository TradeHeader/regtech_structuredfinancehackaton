package cdm.base.datetime;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.datetime.BusinessCenters;
import cdm.base.datetime.BusinessCenters.BusinessCentersBuilder;
import cdm.base.datetime.BusinessCenters.BusinessCentersBuilderImpl;
import cdm.base.datetime.BusinessCenters.BusinessCentersImpl;
import cdm.base.datetime.CommodityBusinessCalendarEnum;
import cdm.base.datetime.meta.BusinessCentersMeta;
import cdm.base.datetime.metafields.FieldWithMetaBusinessCenterEnum;
import cdm.base.datetime.metafields.FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder;
import cdm.base.datetime.metafields.FieldWithMetaCommodityBusinessCalendarEnum;
import cdm.base.datetime.metafields.FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder;
import cdm.base.datetime.metafields.ReferenceWithMetaBusinessCenters;
import cdm.base.datetime.metafields.ReferenceWithMetaBusinessCenters.ReferenceWithMetaBusinessCentersBuilder;
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
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A class for specifying the business day calendar location used in determining whether a day is a business day or not, either by specifying this business center by reference to an enumerated list that is maintained by the FpML standard, or by reference to such specification when it exists elsewhere as part of the instance document. This class corresponds to the FpML BusinessCentersOrReference.model.
 * @version ${project.version}
 */
@RosettaDataType(value="BusinessCenters", builder=BusinessCenters.BusinessCentersBuilderImpl.class, version="${project.version}")
public interface BusinessCenters extends RosettaModelObject, GlobalKey {

	BusinessCentersMeta metaData = new BusinessCentersMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A code identifying one or several business day calendar location(s). The set of business day calendar locations are specified by the business day calendar location enumeration which is maintained by the FpML standard.
	 */
	List<? extends FieldWithMetaBusinessCenterEnum> getBusinessCenter();
	List<? extends FieldWithMetaCommodityBusinessCalendarEnum> getCommodityBusinessCalendar();
	/**
	 * A reference to a financial business center location specified elsewhere in the instance document.
	 */
	ReferenceWithMetaBusinessCenters getBusinessCentersReference();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	BusinessCenters build();
	
	BusinessCenters.BusinessCentersBuilder toBuilder();
	
	static BusinessCenters.BusinessCentersBuilder builder() {
		return new BusinessCenters.BusinessCentersBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends BusinessCenters> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends BusinessCenters> getType() {
		return BusinessCenters.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("businessCenter"), processor, FieldWithMetaBusinessCenterEnum.class, getBusinessCenter());
		processRosetta(path.newSubPath("commodityBusinessCalendar"), processor, FieldWithMetaCommodityBusinessCalendarEnum.class, getCommodityBusinessCalendar());
		processRosetta(path.newSubPath("businessCentersReference"), processor, ReferenceWithMetaBusinessCenters.class, getBusinessCentersReference());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface BusinessCentersBuilder extends BusinessCenters, RosettaModelObjectBuilder {
		FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder getOrCreateBusinessCenter(int _index);
		List<? extends FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder> getBusinessCenter();
		FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder getOrCreateCommodityBusinessCalendar(int _index);
		List<? extends FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder> getCommodityBusinessCalendar();
		ReferenceWithMetaBusinessCenters.ReferenceWithMetaBusinessCentersBuilder getOrCreateBusinessCentersReference();
		ReferenceWithMetaBusinessCenters.ReferenceWithMetaBusinessCentersBuilder getBusinessCentersReference();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		BusinessCenters.BusinessCentersBuilder addBusinessCenter(FieldWithMetaBusinessCenterEnum businessCenter0);
		BusinessCenters.BusinessCentersBuilder addBusinessCenter(FieldWithMetaBusinessCenterEnum businessCenter1, int _idx);
		BusinessCenters.BusinessCentersBuilder addBusinessCenterValue(BusinessCenterEnum businessCenter2);
		BusinessCenters.BusinessCentersBuilder addBusinessCenterValue(BusinessCenterEnum businessCenter3, int _idx);
		BusinessCenters.BusinessCentersBuilder addBusinessCenter(List<? extends FieldWithMetaBusinessCenterEnum> businessCenter4);
		BusinessCenters.BusinessCentersBuilder setBusinessCenter(List<? extends FieldWithMetaBusinessCenterEnum> businessCenter5);
		BusinessCenters.BusinessCentersBuilder addBusinessCenterValue(List<? extends BusinessCenterEnum> businessCenter6);
		BusinessCenters.BusinessCentersBuilder setBusinessCenterValue(List<? extends BusinessCenterEnum> businessCenter7);
		BusinessCenters.BusinessCentersBuilder addCommodityBusinessCalendar(FieldWithMetaCommodityBusinessCalendarEnum commodityBusinessCalendar0);
		BusinessCenters.BusinessCentersBuilder addCommodityBusinessCalendar(FieldWithMetaCommodityBusinessCalendarEnum commodityBusinessCalendar1, int _idx);
		BusinessCenters.BusinessCentersBuilder addCommodityBusinessCalendarValue(CommodityBusinessCalendarEnum commodityBusinessCalendar2);
		BusinessCenters.BusinessCentersBuilder addCommodityBusinessCalendarValue(CommodityBusinessCalendarEnum commodityBusinessCalendar3, int _idx);
		BusinessCenters.BusinessCentersBuilder addCommodityBusinessCalendar(List<? extends FieldWithMetaCommodityBusinessCalendarEnum> commodityBusinessCalendar4);
		BusinessCenters.BusinessCentersBuilder setCommodityBusinessCalendar(List<? extends FieldWithMetaCommodityBusinessCalendarEnum> commodityBusinessCalendar5);
		BusinessCenters.BusinessCentersBuilder addCommodityBusinessCalendarValue(List<? extends CommodityBusinessCalendarEnum> commodityBusinessCalendar6);
		BusinessCenters.BusinessCentersBuilder setCommodityBusinessCalendarValue(List<? extends CommodityBusinessCalendarEnum> commodityBusinessCalendar7);
		BusinessCenters.BusinessCentersBuilder setBusinessCentersReference(ReferenceWithMetaBusinessCenters businessCentersReference0);
		BusinessCenters.BusinessCentersBuilder setBusinessCentersReferenceValue(BusinessCenters businessCentersReference1);
		BusinessCenters.BusinessCentersBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("businessCenter"), processor, FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder.class, getBusinessCenter());
			processRosetta(path.newSubPath("commodityBusinessCalendar"), processor, FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder.class, getCommodityBusinessCalendar());
			processRosetta(path.newSubPath("businessCentersReference"), processor, ReferenceWithMetaBusinessCenters.ReferenceWithMetaBusinessCentersBuilder.class, getBusinessCentersReference());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		BusinessCenters.BusinessCentersBuilder prune();
	}

	/*********************** Immutable Implementation of BusinessCenters  ***********************/
	class BusinessCentersImpl implements BusinessCenters {
		private final List<? extends FieldWithMetaBusinessCenterEnum> businessCenter;
		private final List<? extends FieldWithMetaCommodityBusinessCalendarEnum> commodityBusinessCalendar;
		private final ReferenceWithMetaBusinessCenters businessCentersReference;
		private final MetaFields meta;
		
		protected BusinessCentersImpl(BusinessCenters.BusinessCentersBuilder builder) {
			this.businessCenter = ofNullable(builder.getBusinessCenter()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.commodityBusinessCalendar = ofNullable(builder.getCommodityBusinessCalendar()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.businessCentersReference = ofNullable(builder.getBusinessCentersReference()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("businessCenter")
		public List<? extends FieldWithMetaBusinessCenterEnum> getBusinessCenter() {
			return businessCenter;
		}
		
		@Override
		@RosettaAttribute("commodityBusinessCalendar")
		public List<? extends FieldWithMetaCommodityBusinessCalendarEnum> getCommodityBusinessCalendar() {
			return commodityBusinessCalendar;
		}
		
		@Override
		@RosettaAttribute("businessCentersReference")
		public ReferenceWithMetaBusinessCenters getBusinessCentersReference() {
			return businessCentersReference;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public BusinessCenters build() {
			return this;
		}
		
		@Override
		public BusinessCenters.BusinessCentersBuilder toBuilder() {
			BusinessCenters.BusinessCentersBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(BusinessCenters.BusinessCentersBuilder builder) {
			ofNullable(getBusinessCenter()).ifPresent(builder::setBusinessCenter);
			ofNullable(getCommodityBusinessCalendar()).ifPresent(builder::setCommodityBusinessCalendar);
			ofNullable(getBusinessCentersReference()).ifPresent(builder::setBusinessCentersReference);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BusinessCenters _that = getType().cast(o);
		
			if (!ListEquals.listEquals(businessCenter, _that.getBusinessCenter())) return false;
			if (!ListEquals.listEquals(commodityBusinessCalendar, _that.getCommodityBusinessCalendar())) return false;
			if (!Objects.equals(businessCentersReference, _that.getBusinessCentersReference())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (businessCenter != null ? businessCenter.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (commodityBusinessCalendar != null ? commodityBusinessCalendar.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (businessCentersReference != null ? businessCentersReference.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BusinessCenters {" +
				"businessCenter=" + this.businessCenter + ", " +
				"commodityBusinessCalendar=" + this.commodityBusinessCalendar + ", " +
				"businessCentersReference=" + this.businessCentersReference + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of BusinessCenters  ***********************/
	class BusinessCentersBuilderImpl implements BusinessCenters.BusinessCentersBuilder, GlobalKeyBuilder {
	
		protected List<FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder> businessCenter = new ArrayList<>();
		protected List<FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder> commodityBusinessCalendar = new ArrayList<>();
		protected ReferenceWithMetaBusinessCenters.ReferenceWithMetaBusinessCentersBuilder businessCentersReference;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public BusinessCentersBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("businessCenter")
		public List<? extends FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder> getBusinessCenter() {
			return businessCenter;
		}
		
		public FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder getOrCreateBusinessCenter(int _index) {
		
			if (businessCenter==null) {
				this.businessCenter = new ArrayList<>();
			}
			FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder result;
			return getIndex(businessCenter, _index, () -> {
						FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder newBusinessCenter = FieldWithMetaBusinessCenterEnum.builder();
						return newBusinessCenter;
					});
		}
		
		@Override
		@RosettaAttribute("commodityBusinessCalendar")
		public List<? extends FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder> getCommodityBusinessCalendar() {
			return commodityBusinessCalendar;
		}
		
		public FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder getOrCreateCommodityBusinessCalendar(int _index) {
		
			if (commodityBusinessCalendar==null) {
				this.commodityBusinessCalendar = new ArrayList<>();
			}
			FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder result;
			return getIndex(commodityBusinessCalendar, _index, () -> {
						FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder newCommodityBusinessCalendar = FieldWithMetaCommodityBusinessCalendarEnum.builder();
						return newCommodityBusinessCalendar;
					});
		}
		
		@Override
		@RosettaAttribute("businessCentersReference")
		public ReferenceWithMetaBusinessCenters.ReferenceWithMetaBusinessCentersBuilder getBusinessCentersReference() {
			return businessCentersReference;
		}
		
		@Override
		public ReferenceWithMetaBusinessCenters.ReferenceWithMetaBusinessCentersBuilder getOrCreateBusinessCentersReference() {
			ReferenceWithMetaBusinessCenters.ReferenceWithMetaBusinessCentersBuilder result;
			if (businessCentersReference!=null) {
				result = businessCentersReference;
			}
			else {
				result = businessCentersReference = ReferenceWithMetaBusinessCenters.builder();
			}
			
			return result;
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
		public BusinessCenters.BusinessCentersBuilder addBusinessCenter(FieldWithMetaBusinessCenterEnum businessCenter) {
			if (businessCenter!=null) this.businessCenter.add(businessCenter.toBuilder());
			return this;
		}
		
		@Override
		public BusinessCenters.BusinessCentersBuilder addBusinessCenter(FieldWithMetaBusinessCenterEnum businessCenter, int _idx) {
			getIndex(this.businessCenter, _idx, () -> businessCenter.toBuilder());
			return this;
		}
		
		@Override
		public BusinessCenters.BusinessCentersBuilder addBusinessCenterValue(BusinessCenterEnum businessCenter) {
			this.getOrCreateBusinessCenter(-1).setValue(businessCenter);
			return this;
		}
		
		@Override
		public BusinessCenters.BusinessCentersBuilder addBusinessCenterValue(BusinessCenterEnum businessCenter, int _idx) {
			this.getOrCreateBusinessCenter(_idx).setValue(businessCenter);
			return this;
		}
		@Override 
		public BusinessCenters.BusinessCentersBuilder addBusinessCenter(List<? extends FieldWithMetaBusinessCenterEnum> businessCenters) {
			if (businessCenters != null) {
				for (FieldWithMetaBusinessCenterEnum toAdd : businessCenters) {
					this.businessCenter.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("businessCenter")
		public BusinessCenters.BusinessCentersBuilder setBusinessCenter(List<? extends FieldWithMetaBusinessCenterEnum> businessCenters) {
			if (businessCenters == null)  {
				this.businessCenter = new ArrayList<>();
			}
			else {
				this.businessCenter = businessCenters.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public BusinessCenters.BusinessCentersBuilder addBusinessCenterValue(List<? extends BusinessCenterEnum> businessCenters) {
			if (businessCenters != null) {
				for (BusinessCenterEnum toAdd : businessCenters) {
					this.addBusinessCenterValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public BusinessCenters.BusinessCentersBuilder setBusinessCenterValue(List<? extends BusinessCenterEnum> businessCenters) {
			this.businessCenter.clear();
			if (businessCenters!=null) {
				businessCenters.forEach(this::addBusinessCenterValue);
			}
			return this;
		}
		
		@Override
		public BusinessCenters.BusinessCentersBuilder addCommodityBusinessCalendar(FieldWithMetaCommodityBusinessCalendarEnum commodityBusinessCalendar) {
			if (commodityBusinessCalendar!=null) this.commodityBusinessCalendar.add(commodityBusinessCalendar.toBuilder());
			return this;
		}
		
		@Override
		public BusinessCenters.BusinessCentersBuilder addCommodityBusinessCalendar(FieldWithMetaCommodityBusinessCalendarEnum commodityBusinessCalendar, int _idx) {
			getIndex(this.commodityBusinessCalendar, _idx, () -> commodityBusinessCalendar.toBuilder());
			return this;
		}
		
		@Override
		public BusinessCenters.BusinessCentersBuilder addCommodityBusinessCalendarValue(CommodityBusinessCalendarEnum commodityBusinessCalendar) {
			this.getOrCreateCommodityBusinessCalendar(-1).setValue(commodityBusinessCalendar);
			return this;
		}
		
		@Override
		public BusinessCenters.BusinessCentersBuilder addCommodityBusinessCalendarValue(CommodityBusinessCalendarEnum commodityBusinessCalendar, int _idx) {
			this.getOrCreateCommodityBusinessCalendar(_idx).setValue(commodityBusinessCalendar);
			return this;
		}
		@Override 
		public BusinessCenters.BusinessCentersBuilder addCommodityBusinessCalendar(List<? extends FieldWithMetaCommodityBusinessCalendarEnum> commodityBusinessCalendars) {
			if (commodityBusinessCalendars != null) {
				for (FieldWithMetaCommodityBusinessCalendarEnum toAdd : commodityBusinessCalendars) {
					this.commodityBusinessCalendar.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("commodityBusinessCalendar")
		public BusinessCenters.BusinessCentersBuilder setCommodityBusinessCalendar(List<? extends FieldWithMetaCommodityBusinessCalendarEnum> commodityBusinessCalendars) {
			if (commodityBusinessCalendars == null)  {
				this.commodityBusinessCalendar = new ArrayList<>();
			}
			else {
				this.commodityBusinessCalendar = commodityBusinessCalendars.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public BusinessCenters.BusinessCentersBuilder addCommodityBusinessCalendarValue(List<? extends CommodityBusinessCalendarEnum> commodityBusinessCalendars) {
			if (commodityBusinessCalendars != null) {
				for (CommodityBusinessCalendarEnum toAdd : commodityBusinessCalendars) {
					this.addCommodityBusinessCalendarValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public BusinessCenters.BusinessCentersBuilder setCommodityBusinessCalendarValue(List<? extends CommodityBusinessCalendarEnum> commodityBusinessCalendars) {
			this.commodityBusinessCalendar.clear();
			if (commodityBusinessCalendars!=null) {
				commodityBusinessCalendars.forEach(this::addCommodityBusinessCalendarValue);
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("businessCentersReference")
		public BusinessCenters.BusinessCentersBuilder setBusinessCentersReference(ReferenceWithMetaBusinessCenters businessCentersReference) {
			this.businessCentersReference = businessCentersReference==null?null:businessCentersReference.toBuilder();
			return this;
		}
		@Override
		public BusinessCenters.BusinessCentersBuilder setBusinessCentersReferenceValue(BusinessCenters businessCentersReference) {
			this.getOrCreateBusinessCentersReference().setValue(businessCentersReference);
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public BusinessCenters.BusinessCentersBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public BusinessCenters build() {
			return new BusinessCenters.BusinessCentersImpl(this);
		}
		
		@Override
		public BusinessCenters.BusinessCentersBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BusinessCenters.BusinessCentersBuilder prune() {
			businessCenter = businessCenter.stream().filter(b->b!=null).<FieldWithMetaBusinessCenterEnum.FieldWithMetaBusinessCenterEnumBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			commodityBusinessCalendar = commodityBusinessCalendar.stream().filter(b->b!=null).<FieldWithMetaCommodityBusinessCalendarEnum.FieldWithMetaCommodityBusinessCalendarEnumBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (businessCentersReference!=null && !businessCentersReference.prune().hasData()) businessCentersReference = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getBusinessCenter()!=null && !getBusinessCenter().isEmpty()) return true;
			if (getCommodityBusinessCalendar()!=null && !getCommodityBusinessCalendar().isEmpty()) return true;
			if (getBusinessCentersReference()!=null && getBusinessCentersReference().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BusinessCenters.BusinessCentersBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			BusinessCenters.BusinessCentersBuilder o = (BusinessCenters.BusinessCentersBuilder) other;
			
			merger.mergeRosetta(getBusinessCenter(), o.getBusinessCenter(), this::getOrCreateBusinessCenter);
			merger.mergeRosetta(getCommodityBusinessCalendar(), o.getCommodityBusinessCalendar(), this::getOrCreateCommodityBusinessCalendar);
			merger.mergeRosetta(getBusinessCentersReference(), o.getBusinessCentersReference(), this::setBusinessCentersReference);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BusinessCenters _that = getType().cast(o);
		
			if (!ListEquals.listEquals(businessCenter, _that.getBusinessCenter())) return false;
			if (!ListEquals.listEquals(commodityBusinessCalendar, _that.getCommodityBusinessCalendar())) return false;
			if (!Objects.equals(businessCentersReference, _that.getBusinessCentersReference())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (businessCenter != null ? businessCenter.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (commodityBusinessCalendar != null ? commodityBusinessCalendar.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (businessCentersReference != null ? businessCentersReference.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BusinessCentersBuilder {" +
				"businessCenter=" + this.businessCenter + ", " +
				"commodityBusinessCalendar=" + this.commodityBusinessCalendar + ", " +
				"businessCentersReference=" + this.businessCentersReference + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
